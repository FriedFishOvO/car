<script setup lang="ts">
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { reactive, ref, onMounted } from 'vue'
import useUserInfo from '../store'
import router from '../router'
const activeIndex = ref('1')

const loginVisible = ref(false)
const modifyVisible = ref(false)
const registerVisible = ref(false)
const formLabelWidth = '70px'

// 登录时提交的表单
const loginForm = reactive({
    username: '',
    password: ''
})

// 修改个人信息时提交的表单
const modifyForm = reactive({
    loginId: localStorage.getItem('loginId'),
    nickname: localStorage.getItem('nickname'),
    password: '',
    password1: ''
})

// 注册时提交的表单
const registerForm = reactive({
    nickname: '',
    username: '',
    password: '',
    password1: ''
})

const user = useUserInfo()

const isLogin = ref(false)

// 登录
async function login() {
    if (loginForm.username == '' && loginForm.password == '')
        ElMessage.error('用户名和密码不能为空')
    else if (loginForm.username == '')
        ElMessage.error('用户名不能为空')
    else if (loginForm.password == '')
        ElMessage.error('密码不能为空')
    else {
        const resp = await axios.post('/login', loginForm)
        if (resp.data.code == 1) {
            const loginId = resp.data.data.loginId
            const nickname = resp.data.data.nickname
            const type = resp.data.data.type
            user.setUser(loginId, nickname, type)
            localStorage.setItem('loginId', user.loginId)
            localStorage.setItem('nickname', user.nickname)
            localStorage.setItem('type', user.type)
            isLogin.value = true
            isAdmin.value = localStorage.getItem('type') == '0' ? true : false
            loginVisible.value = false
            ElMessage.success('登录成功')
        } else {
            ElMessage.error('用户名或密码错误')
        }
    }
}

// 退出登录
async function logout() {
    const loginId = localStorage.getItem('loginId') || ''
    const nickname = localStorage.getItem('nickname') || ''
    const type = localStorage.getItem('type') || ''
    user.setUser(loginId, nickname, type)
    const resp = await axios.post('/logout', user)
    if (resp.data.code == 1) {
        isLogin.value = false
        isAdmin.value = false
        localStorage.removeItem('loginId')
        localStorage.removeItem('nickname')
        localStorage.removeItem('type')
        loginForm.username = ''
        loginForm.password = ''
        router.push('/')
        ElMessage.success('退出登录成功')
    }
}

// 修改个人信息
async function modify() {
    if (modifyForm.password != modifyForm.password1) {
        ElMessage.error('两次输入的密码不一致')
    } else if (modifyForm.nickname != '') {
        const resp = await axios.put('/modify', modifyForm)
        if (resp.data.code == 1) {
            localStorage.setItem('nickname', modifyForm.nickname || '')
            modifyVisible.value = false
            ElMessage.success('个人信息修改成功')
        }
    } else {
        modifyVisible.value = false
    }
}

// 注册
async function register() {
    if (registerForm.nickname != '' && registerForm.username != '' && registerForm.password != '') {
        if (registerForm.password != registerForm.password1) {
            ElMessage.error('两次输入的密码不一致')
        } else {
            const resp = await axios.post('/register', registerForm)
            if (resp.data.code == 1) {
                registerVisible.value = false
                ElMessage.success('注册成功')
            } else {
                ElMessage.error('用户名已存在')
            }
        }
    } else {
        ElMessage.error('昵称、用户名、密码均不能为空')
    }
}

const avatarURL = 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'

const isAdmin = ref(false)

onMounted(() => {
    isLogin.value = localStorage.getItem('loginId') ? true : false
    user.nickname = localStorage.getItem('nickname') || ''
    isAdmin.value = localStorage.getItem('type') == '0' ? true : false
    activeIndex.value = sessionStorage.getItem('activeIndex') || '1'
})

function handleCommand(command: string) {
    if (command == '1') {
        router.push(`/favorites/${localStorage.getItem('loginId')}`)
    }
    if (command == '2') {
        modifyVisible.value = true
    }
    if (command == '3') {
        logout()
    }
}

function handleSelect(index: string) {
    if (index == '1') {
        sessionStorage.setItem('activeIndex', '1')
        router.push('/')
    }
    if (index == '2') {
        sessionStorage.setItem('activeIndex', '2')
        router.push('/manage')
    }
}
</script>

<template>
    <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect">
        <el-menu-item style="left: 10%;" index="1">首页</el-menu-item>
        <el-menu-item v-if="isAdmin" style="left: 10%;" index="2">车辆管理</el-menu-item>
        <!-- 登录按钮 -->
        <div class="login-btn">
            <el-button v-if="!isLogin" text @click="registerVisible = true">注册</el-button>
            <el-divider direction="vertical"></el-divider>
            <el-button v-if="!isLogin" text @click="loginVisible = true">登录</el-button>
        </div>

        <el-dropdown v-if="isLogin" @command="handleCommand" class="dropdown">
            <span style="display: flex;align-items: center;justify-content: center;">
                <el-avatar class="avatar" :src="avatarURL" />
                <el-text class="text">{{ user.nickname }}</el-text>
            </span>

            <template #dropdown>
                <el-dropdown-menu>
                    <el-dropdown-item command="1">我的收藏夹</el-dropdown-item>
                    <el-dropdown-item command="2">修改个人信息</el-dropdown-item>
                    <el-dropdown-item command="3">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </template>
        </el-dropdown>
    </el-menu>

    <!-- 登录对话框 -->
    <el-dialog v-model="loginVisible" title="登录" width="500px">
        <el-form :model="loginForm">
            <el-form-item label="用户名" :label-width="formLabelWidth">
                <el-input v-model="loginForm.username" placeholder="请输入用户名" />
            </el-form-item>
            <el-form-item label="密码" :label-width="formLabelWidth">
                <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="loginVisible = false">取消</el-button>
                <el-button type="primary" @click="login">确定</el-button>
            </span>
        </template>
    </el-dialog>

    <!-- 修改个人信息对话框 -->
    <el-dialog v-model="modifyVisible" title="修改个人信息" width="500px">
        <el-form :model="modifyForm">
            <el-form-item label="昵称" :label-width="formLabelWidth">
                <el-input v-model="modifyForm.nickname" placeholder="请输入修改后的昵称" />
            </el-form-item>
            <el-form-item label="密码" :label-width="formLabelWidth">
                <el-input type="password" v-model="modifyForm.password" placeholder="请输入修改后的密码" />
            </el-form-item>
            <el-form-item label="确认密码" :label-width="formLabelWidth">
                <el-input type="password" v-model="modifyForm.password1" placeholder="请再次输入修改后的密码" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="modifyVisible = false">取消</el-button>
                <el-button type="primary" @click="modify">确定</el-button>
            </span>
        </template>
    </el-dialog>

    <!-- 注册对话框 -->
    <el-dialog v-model="registerVisible" title="注册" width="500px">
        <el-form :model="registerForm">
            <el-form-item label="昵称" :label-width="formLabelWidth">
                <el-input v-model="registerForm.nickname" placeholder="请输入昵称" />
            </el-form-item>
            <el-form-item label="用户名" :label-width="formLabelWidth">
                <el-input v-model="registerForm.username" placeholder="请输入用户名" />
            </el-form-item>
            <el-form-item label="密码" :label-width="formLabelWidth">
                <el-input type="password" v-model="registerForm.password" placeholder="请输入密码" />
            </el-form-item>
            <el-form-item label="确认密码" :label-width="formLabelWidth">
                <el-input type="password" v-model="registerForm.password1" placeholder="请再次输入密码" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="registerVisible = false">取消</el-button>
                <el-button type="primary" @click="register">确定</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<style scoped>
.login-btn {
    position: absolute;
    right: 10%;
    top: 20%;
}

.dropdown {
    position: absolute;
    right: 10%;
    top: 10%;
    border: 0;
}

.text {
    margin-left: 10px;
    font-weight: bold;
}

.dropdown:hover {
    cursor: pointer;
}
</style>
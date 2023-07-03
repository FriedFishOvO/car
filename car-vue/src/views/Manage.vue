<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { pcTextArr } from 'element-china-area-data'
import axios from 'axios'
import router from '../router'
import { ElMessage } from 'element-plus'

const car = reactive({
    list: [] as any,
    data: {} as any
})

onMounted(async () => {
    handleSearch()
})

const searchInfo = ref('')

async function handleSearch() {
    const resp = await axios.get('/car/search', {
        params: {
            searchInfo: searchInfo.value
        }
    })
    car.list = resp.data.data
    total.value = car.list.length
}

//分页相关数据
const currentPage = ref(1)
const pageSize = 10
const total = ref()

// 处理分页变化
const handlePageChange = (newPage: number) => {
    currentPage.value = newPage
}

function detail(row: any) {
    router.push(`/car/${row.id}`)
}

async function deleteCar(row: any) {
    const resp = await axios.delete(`/car/delete/${row.id}`)
    if (resp.data.code == 1) {
        handleSearch()
        ElMessage.success('删除成功')
    }
}

async function updateCar(row: any) {
    updateVisible.value = true
    const resp = await axios.get(`/car/${row.id}`)
    car.data = resp.data.data
    updateForm.id = car.data.id
    updateForm.name = car.data.name
    updateForm.brand = car.data.brand
    updateForm.image = car.data.image
    updateForm.price = car.data.price
    updateForm.color = car.data.color
    updateForm.fuelType = car.data.fuelType
    updateForm.transmission = car.data.transmission
    updateForm.displacement = car.data.displacement
    updateForm.fuelConsumption = car.data.fuelConsumption
    updateForm.batteryRange = car.data.batteryRange
    updateForm.odometer = car.data.odometer
    updateForm.model = car.data.model
    updateForm.capacity = car.data.capacity
    updateForm.registrationDate = car.data.registrationDate
    const province = car.data.registrationProvince
    const city = car.data.registrationCity
    updateForm.registrationArea = [province, city]
}

const formLabelWidth = '150px'

const addVisible = ref(false)
const updateVisible = ref(false)

const addForm = reactive({
    name: '',
    brand: '',
    image: '',
    price: '',
    color: '',
    fuelType: '',
    transmission: '',
    displacement: '',
    fuelConsumption: '',
    batteryRange: '',
    odometer: '',
    model: '',
    capacity: 2,
    registrationDate: '',
    registrationArea: ''
})

const updateForm = reactive({
    id: '',
    name: '',
    brand: '',
    image: '',
    price: '',
    color: '',
    fuelType: '',
    transmission: '',
    displacement: '',
    fuelConsumption: '',
    batteryRange: '',
    odometer: '',
    model: '',
    capacity: 2,
    registrationDate: '',
    registrationArea: ['', '']
})

async function add() {
    if (addForm.name != '' && addForm.brand != '' && addForm.image != '' && addForm.price != '' && addForm.color != '' && addForm.fuelType != '' && addForm.transmission != '' && addForm.odometer != '' && addForm.model != '' && addForm.registrationDate != null && addForm.registrationArea != null) {
        if (addForm.fuelType == '汽油') {
            if (addForm.displacement == '' || addForm.fuelConsumption == '') {
                ElMessage.error('任意一项不能为空')
            } else {
                const resp = await axios.post('/car/add', addForm)
                if (resp.data.code == 1) {
                    handleSearch()
                    addVisible.value = false
                    ElMessage.success('添加成功')
                }
            }
        } else if (addForm.fuelType == '电动') {
            if (addForm.batteryRange == '') {
                ElMessage.error('任意一项不能为空')
            } else {
                const resp = await axios.post('/car/add', addForm)
                if (resp.data.code == 1) {
                    handleSearch()
                    addVisible.value = false
                    ElMessage.success('添加成功')
                }
            }
        }
    } else {
        ElMessage.error('任意一项不能为空')
    }
}

const brandOptions = [
    { label: '大众', value: '大众' },
    { label: '本田', value: '本田' },
    { label: '宝马', value: '宝马' },
    { label: '丰田', value: '丰田' },
    { label: '奥迪', value: '奥迪' },
    { label: '别克', value: '别克' },
    { label: '奔驰', value: '奔驰' },
    { label: '吉利', value: '吉利' },
    { label: '哈弗', value: '哈弗' },
    { label: '日产', value: '日产' }
]

const colorOptions = [
    { label: '黑色', value: '黑色' },
    { label: '白色', value: '白色' },
    { label: '银灰色', value: '银灰色' },
    { label: '深灰色', value: '深灰色' },
    { label: '红色', value: '红色' },
    { label: '香槟色', value: '香槟色' }
]

const fuelTypeOptions = [
    { label: '汽油', value: '汽油' },
    { label: '电动', value: '电动' }
]

const transmissionOptions = [
    { label: '手动', value: '手动' },
    { label: '自动', value: '自动' }
]

const modelOptions = [
    { label: '三厢车', value: '三厢车' },
    { label: '两厢车', value: '两厢车' },
    { label: 'SUV', value: 'SUV' },
    { label: '跑车', value: '跑车' },
    { label: '敞篷车', value: '敞篷车' }
]

async function update() {
    if (updateForm.name != '' && updateForm.brand != '' && updateForm.image != '' && updateForm.price != '' && updateForm.color != '' && updateForm.fuelType != '' && updateForm.transmission != '' && updateForm.odometer != '' && updateForm.model != '' && updateForm.registrationDate != null && updateForm.registrationArea != null) {
        if (updateForm.fuelType == '汽油') {
            if (updateForm.displacement == '' || updateForm.fuelConsumption == '') {
                ElMessage.error('任意一项不能为空')
            } else {
                const resp = await axios.put('/car/update', updateForm)
                if (resp.data.code == 1) {
                    handleSearch()
                    updateVisible.value = false
                    ElMessage.success('修改成功')
                }
            }
        } else if (updateForm.fuelType == '电动') {
            if (updateForm.batteryRange == '') {
                ElMessage.error('任意一项不能为空')
            } else {
                const resp = await axios.put('/car/update', updateForm)
                if (resp.data.code == 1) {
                    handleSearch()
                    updateVisible.value = false
                    ElMessage.success('修改成功')
                }
            }
        }
    } else {
        ElMessage.error('任意一项不能为空')
    }
}
</script>

<template>
    <el-main class="main">
        <el-space alignment="normal" class="container">
            <div style="display: flex;justify-content: center;">
                <el-input v-model="searchInfo" class="search" placeholder="搜索" @change="handleSearch" clearable>
                </el-input>

                <el-button type="success" style="margin-left: auto;" @click="addVisible = true">新增</el-button>
            </div>

            <el-table :data="car.list.slice((currentPage - 1) * pageSize, currentPage * pageSize)" border
                style="width: 100%; margin-top: 20px;">
                <el-table-column prop="name" label="名称" align="center" width="400px" />
                <el-table-column prop="brand" label="品牌" align="center" width="60px" />
                <el-table-column prop="price" label="价格(万)" align="center" width="100px" />
                <el-table-column prop="color" label="颜色" align="center" width="80px" />
                <el-table-column prop="fuelType" label="能源类型" align="center" width="90px" />
                <el-table-column prop="registrationProvince" label="上牌省份" align="center" width="130px" />
                <el-table-column prop="registrationCity" label="上牌城市" align="center" width="120px" />
                <el-table-column prop="createTime" label="上架时间" align="center" />
                <el-table-column label="操作" align="center" width="260px">
                    <template #default="scope">
                        <el-button type="primary" @click="detail(scope.row)">查看详情</el-button>
                        <el-button type="warning" @click="updateCar(scope.row)">修改</el-button>
                        <el-button type="danger" @click="deleteCar(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <!-- 分页栏 -->
            <div style="display: flex; justify-content: center;">
                <el-pagination class="pagination" background layout="prev, pager, next" :current-page="currentPage"
                    :page-size="pageSize" :total="total" @current-change="handlePageChange" />
            </div>

            <!-- 新增对话框 -->
            <el-dialog v-model="addVisible" title="新增" width="750px">
                <el-form :model="addForm">
                    <el-form-item label="名称" :label-width="formLabelWidth">
                        <el-input v-model="addForm.name" placeholder="请输入名称" />
                    </el-form-item>
                    <el-form-item label="品牌" :label-width="formLabelWidth">
                        <el-select v-model="addForm.brand" placeholder="请选择品牌" clearable>
                            <el-option v-for="item in brandOptions" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="图片" :label-width="formLabelWidth">
                        <el-input v-model="addForm.image" placeholder="请输入图片地址" />
                    </el-form-item>
                    <el-form-item label="价格" :label-width="formLabelWidth">
                        <el-input v-model="addForm.price" placeholder="请输入价格(单位: 万)" />
                    </el-form-item>
                    <el-form-item label="颜色" :label-width="formLabelWidth">
                        <el-select v-model="addForm.color" placeholder="请选择颜色" clearable>
                            <el-option v-for="item in colorOptions" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="能源类型" :label-width="formLabelWidth">
                        <el-select v-model="addForm.fuelType" placeholder="请选择能源类型" clearable>
                            <el-option v-for="item in fuelTypeOptions" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="变速箱" :label-width="formLabelWidth">
                        <el-select v-model="addForm.transmission" placeholder="请选择变速箱" clearable>
                            <el-option v-for="item in transmissionOptions" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </el-form-item>
                    <el-form-item v-if="addForm.fuelType == '汽油'" label="排量(L)" :label-width="formLabelWidth">
                        <el-input v-model="addForm.displacement" placeholder="请输入排量(单位: L)" />
                    </el-form-item>
                    <el-form-item v-if="addForm.fuelType == '汽油'" label="综合油耗(L/100km)" :label-width="formLabelWidth">
                        <el-input v-model="addForm.fuelConsumption" placeholder="请输入综合油耗(单位: L/100km)" />
                    </el-form-item>
                    <el-form-item v-if="addForm.fuelType == '电动'" label="电池续航里程(km)" :label-width="formLabelWidth">
                        <el-input v-model="addForm.batteryRange" placeholder="请输入电池续航里程(单位: km)" />
                    </el-form-item>
                    <el-form-item label="表显里程(km)" :label-width="formLabelWidth">
                        <el-input v-model="addForm.odometer" placeholder="请输入表显里程(单位: km)" />
                    </el-form-item>
                    <el-form-item label="车型" :label-width="formLabelWidth">
                        <el-select v-model="addForm.model" placeholder="请选择车型" clearable>
                            <el-option v-for="item in modelOptions" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="座位数" :label-width="formLabelWidth">
                        <el-input-number v-model="addForm.capacity" :min="2" :max="9" />
                    </el-form-item>
                    <el-form-item label="首次上牌日期" :label-width="formLabelWidth">
                        <el-date-picker v-model="addForm.registrationDate" type="day" placeholder="请选择上牌日期" />
                    </el-form-item>
                    <el-form-item label="上牌地区" :label-width="formLabelWidth">
                        <el-cascader v-model="addForm.registrationArea" placeholder="请选择上牌地区" :options="pcTextArr"
                            filterable clearable />
                    </el-form-item>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                        <el-button @click="addVisible = false">取消</el-button>
                        <el-button type="primary" @click="add">确定</el-button>
                    </span>
                </template>
            </el-dialog>

            <!-- 修改对话框 -->
            <el-dialog v-model="updateVisible" title="修改" width="750px">
                <el-form :model="updateForm">
                    <el-form-item label="名称" :label-width="formLabelWidth">
                        <el-input v-model="updateForm.name" placeholder="请输入名称" />
                    </el-form-item>
                    <el-form-item label="品牌" :label-width="formLabelWidth">
                        <el-select v-model="updateForm.brand" placeholder="请选择品牌" clearable>
                            <el-option v-for="item in brandOptions" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="图片" :label-width="formLabelWidth">
                        <el-input v-model="updateForm.image" placeholder="请输入图片地址" />
                    </el-form-item>
                    <el-form-item label="价格" :label-width="formLabelWidth">
                        <el-input v-model="updateForm.price" placeholder="请输入价格(单位: 万)" />
                    </el-form-item>
                    <el-form-item label="颜色" :label-width="formLabelWidth">
                        <el-select v-model="updateForm.color" placeholder="请选择颜色" clearable>
                            <el-option v-for="item in colorOptions" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="能源类型" :label-width="formLabelWidth">
                        <el-select v-model="updateForm.fuelType" placeholder="请选择能源类型" clearable>
                            <el-option v-for="item in fuelTypeOptions" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="变速箱" :label-width="formLabelWidth">
                        <el-select v-model="updateForm.transmission" placeholder="请选择变速箱" clearable>
                            <el-option v-for="item in transmissionOptions" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </el-form-item>
                    <el-form-item v-if="updateForm.fuelType == '汽油'" label="排量(L)" :label-width="formLabelWidth">
                        <el-input v-model="updateForm.displacement" placeholder="请输入排量(单位: L)" />
                    </el-form-item>
                    <el-form-item v-if="updateForm.fuelType == '汽油'" label="综合油耗(L/100km)" :label-width="formLabelWidth">
                        <el-input v-model="updateForm.fuelConsumption" placeholder="请输入综合油耗(单位: L/100km)" />
                    </el-form-item>
                    <el-form-item v-if="updateForm.fuelType == '电动'" label="电池续航里程(km)" :label-width="formLabelWidth">
                        <el-input v-model="updateForm.batteryRange" placeholder="请输入电池续航里程(单位: km)" />
                    </el-form-item>
                    <el-form-item label="表显里程(km)" :label-width="formLabelWidth">
                        <el-input v-model="updateForm.odometer" placeholder="请输入表显里程(单位: km)" />
                    </el-form-item>
                    <el-form-item label="车型" :label-width="formLabelWidth">
                        <el-select v-model="updateForm.model" placeholder="请选择车型" clearable>
                            <el-option v-for="item in modelOptions" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="座位数" :label-width="formLabelWidth">
                        <el-input-number v-model="updateForm.capacity" :min="2" :max="9" />
                    </el-form-item>
                    <el-form-item label="首次上牌日期" :label-width="formLabelWidth">
                        <el-date-picker v-model="updateForm.registrationDate" type="day" placeholder="请选择上牌日期" />
                    </el-form-item>
                    <el-form-item label="上牌地区" :label-width="formLabelWidth">
                        <el-cascader v-model="updateForm.registrationArea" placeholder="请选择上牌地区" :options="pcTextArr"
                            filterable clearable />
                    </el-form-item>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                        <el-button @click="updateVisible = false">取消</el-button>
                        <el-button type="primary" @click="update">确定</el-button>
                    </span>
                </template>
            </el-dialog>
        </el-space>
    </el-main>
</template>

<style scoped>
.main {
    padding: 0;
    display: flex;
    justify-content: center;
    overflow: hidden;
}

.container {
    width: 80%;
    margin-top: 20px;
    display: flex;
    flex-direction: column;
}

.search {
    width: 15%;
}

.pagination {
    margin-top: 20px;
    margin-bottom: 20px;
}
</style>
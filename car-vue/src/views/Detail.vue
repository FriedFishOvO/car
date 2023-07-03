<script setup lang="ts">
import { reactive, onMounted, ref } from 'vue'
import service from '../assets/image/售后保障.png'
import axios from 'axios'
import { ElMessage } from 'element-plus';

const props = defineProps({
    id: Number
})

const car = reactive({
    data: {} as any
})

const isFavorite = ref(false)

const favorite = reactive({
    userId: localStorage.getItem('loginId'),
    carId: props.id
})

async function collect() {
    if (localStorage.getItem('loginId')) {
        const resp = await axios.post('/favorites/add', favorite)
        if (resp.data.code == 1) {
            isFavorite.value = true
            ElMessage.success('收藏成功')
        }
    } else {
        ElMessage.error('请先登录')
    }
}

async function cancel() {
    const resp = await axios.post('/favorites/cancel', favorite)
    if (resp.data.code == 1) {
        isFavorite.value = false
        ElMessage.success('取消收藏成功')
    }
}

onMounted(async () => {
    const resp = await axios.get(`/car/${props.id}`)
    car.data = resp.data.data
    await axios.post('/favorites/check', favorite).then(res => {
        isFavorite.value = res.data.data == 1 ? true : false
    })
})
</script>

<template>
    <el-main class="main">
        <el-space alignment="normal" direction="vertical" class="container">

            <el-row>
                <!-- 预览图 -->
                <el-col :span="12">
                    <el-image class="image" :src="car.data.image"></el-image>
                </el-col>

                <!-- 右侧信息 -->
                <el-col :span="12" style="padding-left: 20px;">

                    <el-space alignment="normal" direction="vertical" :size="15">
                        <!-- 标题 -->
                        <el-text class="title">
                            {{ car.data.name }}
                        </el-text>

                        <!-- 详细信息 -->
                        <el-row>
                            <el-col :span="4" style="display: flex; justify-content: center;">
                                <el-space direction="vertical">
                                    <el-text style="font-weight: bold;">
                                        {{ car.data.registrationDate?.substring(0, 7) }}
                                    </el-text>
                                    <el-text>上牌时间</el-text>
                                </el-space>
                            </el-col>

                            <el-divider direction="vertical" class="divider1"></el-divider>

                            <el-col :span="4" style="display: flex; justify-content: center;">
                                <el-space direction="vertical">
                                    <el-text style="font-weight: bold;">
                                        {{ car.data.odometer }}公里
                                    </el-text>
                                    <el-text>表显里程</el-text>
                                </el-space>
                            </el-col>

                            <el-divider direction="vertical" class="divider1"></el-divider>

                            <el-col :span="4" style="display: flex; justify-content: center;">
                                <el-space direction="vertical">
                                    <el-text v-if="car.data.fuelType == '汽油'" style="font-weight: bold;">
                                        {{ car.data.displacement }}L
                                    </el-text>
                                    <el-text v-if="car.data.fuelType == '电动'" style="font-weight: bold;">
                                        --
                                    </el-text>
                                    <el-text>排量</el-text>
                                </el-space>
                            </el-col>

                            <el-divider direction="vertical" class="divider1"></el-divider>

                            <el-col :span="4" style="display: flex; justify-content: center;">
                                <el-space direction="vertical">
                                    <el-text style="font-weight: bold;">
                                        {{ car.data.transmission }}
                                    </el-text>
                                    <el-text>变速箱</el-text>
                                </el-space>
                            </el-col>

                        </el-row>

                        <el-space>
                            <el-text>到手价</el-text>
                            <el-text class="price">
                                {{ car.data.price }}万
                            </el-text>
                        </el-space>

                        <!-- 售后保障 -->
                        <el-image :src="service"></el-image>

                        <!-- 收藏 -->
                        <div class="btn-wrapper" v-if="!isFavorite">
                            <el-button size="large" type="success" class="button" @click="collect">收藏</el-button>
                        </div>

                        <!-- 取消收藏 -->
                        <div class="btn-wrapper" v-if="isFavorite">
                            <el-button size="large" type="danger" class="button" @click="cancel">取消收藏</el-button>
                        </div>
                    </el-space>

                </el-col>

            </el-row>

            <div style="margin-top: 30px;">
                <el-text class="item">基本信息</el-text>
                <el-divider class="divider2" direction="horizontal"></el-divider>
            </div>

            <div>
                <el-row>
                    <el-col :span="3">
                        <el-card class="card-left" shadow="never">
                            <el-text>品牌</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="5">
                        <el-card class="card-right" shadow="never">
                            <el-text>
                                {{ car.data.brand }}
                            </el-text>
                        </el-card>
                    </el-col>

                    <el-col :span="3">
                        <el-card class="card-left" shadow="never">
                            <el-text>变速箱</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="5">
                        <el-card class="card-right" shadow="never">
                            <el-text>
                                {{ car.data.transmission }}
                            </el-text>
                        </el-card>
                    </el-col>

                    <el-col :span="3">
                        <el-card class="card-left" shadow="never">
                            <el-text>首次上牌时间</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="5">
                        <el-card class="card-right" shadow="never">
                            <el-text>
                                {{ car.data.registrationDate?.substring(0, 7) }}
                            </el-text>
                        </el-card>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="3">
                        <el-card class="card-left" shadow="never">
                            <el-text>车身颜色</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="5">
                        <el-card class="card-right" shadow="never">
                            <el-text>
                                {{ car.data.color }}
                            </el-text>
                        </el-card>
                    </el-col>

                    <el-col :span="3">
                        <el-card class="card-left" shadow="never">
                            <el-text>排量</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="5">
                        <el-card class="card-right" shadow="never">
                            <el-text v-if="car.data.fuelType == '汽油'">
                                {{ car.data.displacement }}L
                            </el-text>
                            <el-text v-if="car.data.fuelType == '电动'">
                                --
                            </el-text>
                        </el-card>
                    </el-col>

                    <el-col :span="3">
                        <el-card class="card-left" shadow="never">
                            <el-text>上牌地区</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="5">
                        <el-card class="card-right" shadow="never">
                            <el-text>
                                {{ car.data.registrationProvince }}
                                {{ car.data.registrationCity }}
                            </el-text>
                        </el-card>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="3">
                        <el-card class="card-left" shadow="never">
                            <el-text>车型</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="5">
                        <el-card class="card-right" shadow="never">
                            <el-text>
                                {{ car.data.model }}
                            </el-text>
                        </el-card>
                    </el-col>

                    <el-col :span="3">
                        <el-card class="card-left" shadow="never">
                            <el-text>能源类型</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="5">
                        <el-card class="card-right" shadow="never">
                            <el-text>
                                {{ car.data.fuelType }}
                            </el-text>
                        </el-card>
                    </el-col>

                    <el-col :span="3">
                        <el-card class="card-left" shadow="never">
                            <el-text>车龄</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="5">
                        <el-card class="card-right" shadow="never">
                            <el-text>
                                {{ car.data.age }}年
                            </el-text>
                        </el-card>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="3">
                        <el-card class="card-left" shadow="never">
                            <el-text>座位数</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="5">
                        <el-card class="card-right" shadow="never">
                            <el-text>
                                {{ car.data.capacity }}座
                            </el-text>
                        </el-card>
                    </el-col>

                    <el-col :span="3">
                        <el-card class="card-left" shadow="never">
                            <el-text v-if="car.data.fuelType == '汽油'">综合油耗</el-text>
                            <el-text v-if="car.data.fuelType == '电动'">电池续航里程</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="5">
                        <el-card class="card-right" shadow="never">
                            <el-text v-if="car.data.fuelType == '汽油'">
                                {{ car.data.fuelConsumption }}L/100km
                            </el-text>
                            <el-text v-if="car.data.fuelType == '电动'">
                                {{ car.data.batteryRange }}km
                            </el-text>
                        </el-card>
                    </el-col>

                    <el-col :span="3">
                        <el-card class="card-left" shadow="never">
                            <el-text>表显里程</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="5">
                        <el-card class="card-right" shadow="never">
                            <el-text>
                                {{ car.data.odometer }}公里
                            </el-text>
                        </el-card>
                    </el-col>
                </el-row>
            </div>

            <div style="margin-top: 30px;">
                <el-text class="item">车辆图片</el-text>
                <el-divider class="divider2" direction="horizontal"></el-divider>
            </div>

            <el-image :src="car.data.image"></el-image>

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
    width: 60%;
    margin-top: 20px;
    display: flex;
    flex-direction: column;
}

.title {
    font-size: 30px;
    font-weight: bold;
}

.divider1 {
    border-width: 1px;
    height: 85%;
    color: black;
}

.divider2 {
    margin-top: 10px;
    border-width: 2px;
}

.button {
    border-radius: 0;
    width: 30%;
}

.btn-wrapper {
    width: 100%;
}

.item {
    font-size: 20px;
    font-weight: bold;
}

.price {
    font-size: 30px;
    font-weight: bold;
    color: red;
}

.image {
    height: 375px;
}

.card-left {
    border-radius: 0;
    display: flex;
    justify-content: center;
    background-color: #f7f7f7;
}

.card-right {
    border-radius: 0;
    display: flex;
    justify-content: center;
}
</style>
<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue'
import axios from 'axios'

const car = reactive({
    list: [] as any
})

onMounted(async () => {
    const resp = await axios.get('/car/all')
    car.list = resp.data.data
})

const value1 = ref('')
const value2 = ref('')

const car1 = reactive({
    data: {} as any
})
const car2 = reactive({
    data: {} as any
})

const car1HasValue = ref(false)
const car2HasValue = ref(false)

async function handleChange1() {
    if (value1.value != '') {
        const resp = await axios.get(`/car/${value1.value}`)
        car1.data = resp.data.data
        car1HasValue.value = true
        console.log(car1.data.brand)
    } else {
        car1.data = {}
        car1HasValue.value = false
    }
}

async function handleChange2() {
    if (value2.value != '') {
        const resp = await axios.get(`/car/${value2.value}`)
        car2.data = resp.data.data
        car2HasValue.value = true
        console.log(car2.data.brand)
    } else {
        car2.data = {}
        car2HasValue.value = false
    }
}
</script>

<template>
    <el-main class="main">
        <el-space alignment="normal" direction="vertical" class="container">

            <el-text class="title">车辆比较</el-text>

            <el-divider class="divider" direction="horizontal"></el-divider>

            <!-- 选择器 -->
            <el-row>
                <el-col :span="6"></el-col>
                <el-col :span="9" style="display: flex;justify-content: center;flex-direction: column;">
                    <el-select class="selector" v-model="value1" placeholder="请选择车辆" @change="handleChange1" clearable>
                        <el-option v-for="item in car.list" :label="item.name" :value="item.id">
                        </el-option>
                    </el-select>
                    <el-link target="_blank" :underline="false" :href="'/car/' + car1.data.id">
                        <el-image class="image" v-if="car1HasValue" :src="car1.data.image"></el-image>
                    </el-link>
                </el-col>
                <el-col :span="9" style="display: flex;justify-content: center;flex-direction: column;">
                    <el-select class="selector" v-model="value2" placeholder="请选择车辆" @change="handleChange2" clearable>
                        <el-option v-for="item in car.list" :label="item.name" :value="item.id">
                        </el-option>
                    </el-select>
                    <el-link target="_blank" :underline="false" :href="'/car/' + car2.data.id">
                        <el-image class="image" v-if="car2HasValue" :src="car2.data.image"></el-image>
                    </el-link>
                </el-col>
            </el-row>

            <el-row>

                <!-- 指标名称 -->
                <el-col :span="6" class="column">
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">名称</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">品牌</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">价格</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">颜色</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">排量</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">表显里程</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">变速箱</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">车型</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">座位数</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">能源类型</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">综合油耗</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">电池续航里程</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">首次上牌地区</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">首次上牌时间</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font-weight: bold;font-size: 20px;">车龄</el-text>
                        </el-card>
                    </div>
                </el-col>

                <!-- 左边车辆 -->
                <el-col :span="9" class="column">
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text style="font">{{ car1.data.name }}</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car1.data.brand }}</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car1.data.price }}</el-text>
                            <el-text v-if="car1HasValue">万</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car1.data.color }}</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <div v-if="car1.data.fuelType == '汽油'">
                                <el-text>
                                    {{ car1.data.displacement }}
                                </el-text>
                                <el-text v-if="car1HasValue">L</el-text>
                            </div>
                            <div v-if="car1.data.fuelType == '电动'">
                                <el-text>--</el-text>
                            </div>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car1.data.odometer }}</el-text>
                            <el-text v-if="car1HasValue">公里</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car1.data.transmission }}</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car1.data.model }}</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car1.data.capacity }}</el-text>
                            <el-text v-if="car1HasValue">座</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car1.data.fuelType }}</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <div v-if="car1.data.fuelType == '汽油'">
                                <el-text>{{ car1.data.fuelConsumption }}</el-text>
                                <el-text v-if="car1HasValue">L/100km</el-text>
                            </div>
                            <div v-if="car1.data.fuelType == '电动'">
                                <el-text>--</el-text>
                            </div>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <div v-if="car1.data.fuelType == '汽油'">
                                <el-text>--</el-text>
                            </div>
                            <div v-if="car1.data.fuelType == '电动'">
                                <el-text>{{ car1.data.batteryRange }}</el-text>
                                <el-text v-if="car1HasValue">km</el-text>
                            </div>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>
                                {{ car1.data.registrationProvince }}
                                {{ car1.data.registrationCity }}
                            </el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>
                                {{ car1.data.registrationDate }}
                            </el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car1.data.age }}</el-text>
                            <el-text v-if="car1HasValue">年</el-text>
                        </el-card>
                    </div>
                </el-col>

                <!-- 右边车辆 -->
                <el-col :span="9" class="column">
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car2.data.name }}</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car2.data.brand }}</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car2.data.price }}</el-text>
                            <el-text v-if="car2HasValue">万</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car2.data.color }}</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <div v-if="car2.data.fuelType == '汽油'">
                                <el-text>
                                    {{ car2.data.displacement }}
                                </el-text>
                                <el-text v-if="car2HasValue">L</el-text>
                            </div>
                            <div v-if="car2.data.fuelType == '电动'">
                                <el-text>--</el-text>
                            </div>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car2.data.odometer }}</el-text>
                            <el-text v-if="car2HasValue">公里</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car2.data.transmission }}</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car2.data.model }}</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car2.data.capacity }}</el-text>
                            <el-text v-if="car2HasValue">座</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car2.data.fuelType }}</el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <div v-if="car2.data.fuelType == '汽油'">
                                <el-text>{{ car2.data.fuelConsumption }}</el-text>
                                <el-text v-if="car2HasValue">L/100km</el-text>
                            </div>
                            <div v-if="car2.data.fuelType == '电动'">
                                <el-text>--</el-text>
                            </div>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <div v-if="car2.data.fuelType == '汽油'">
                                <el-text>--</el-text>
                            </div>
                            <div v-if="car2.data.fuelType == '电动'">
                                <el-text>{{ car2.data.batteryRange }}</el-text>
                                <el-text v-if="car2HasValue">km</el-text>
                            </div>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>
                                {{ car2.data.registrationProvince }}
                                {{ car2.data.registrationCity }}
                            </el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>
                                {{ car2.data.registrationDate }}
                            </el-text>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="card" shadow="never">
                            <el-text>{{ car2.data.age }}</el-text>
                            <el-text v-if="car2HasValue">年</el-text>
                        </el-card>
                    </div>
                </el-col>
            </el-row>

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

.column {
    display: flex;
    justify-content: center;
    flex-direction: column;
}

.card {
    border-radius: 0;
    display: flex;
    justify-content: center;
    height: 60px;
}

.title {
    font-size: 30px;
    font-weight: bold;
}

.divider {
    border-width: 2px;
    margin-top: 0px;
}



.selector {
    padding: 10px;
}
</style>
<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import axios from 'axios'
import CarCard from '../components/car-card.vue'
import router from '../router';

const car = reactive({
    list: [] as any
})

onMounted(() => {
    handleChange(activeIndex.value)
})

const searchInfo = ref('')

const brandValue = ref('不限')
const priceValue = ref('不限')

const activeIndex = ref('1')

// 车龄选择器
const ageValue = ref('')
const ageOptions = [
    { label: '1年以内', value: '1年以内' },
    { label: '3年以内', value: '3年以内' },
    { label: '5年以内', value: '5年以内' },
    { label: '8年以内', value: '8年以内' },
    { label: '8年以上', value: '8年以上' }
]

// 里程选择器
const odometerValue = ref('')
const odometerOptions = [
    { label: '1万公里内', value: '1万公里内' },
    { label: '3万公里内', value: '3万公里内' },
    { label: '5万公里内', value: '5万公里内' },
    { label: '8万公里内', value: '8万公里内' },
    { label: '10万公里内', value: '10万公里内' },
    { label: '10万公里以上', value: '10万公里以上' }
]

// 排量选择器
const displacementValue = ref('')
const displacementOptions = [
    { label: '1.0L以下', value: '1.0L以下' },
    { label: '1.0-1.6L', value: '1.0-1.6L' },
    { label: '1.6-2.0L', value: '1.6-2.0L' },
    { label: '2.0-3.0L', value: '2.0-3.0L' },
    { label: '3.0-4.0L', value: '3.0-4.0L' },
    { label: '4.0L以上', value: '4.0L以上' }
]

// 变速箱选择器
const transmissionValue = ref('')
const transmissionOptions = [
    { label: '手动', value: '手动' },
    { label: '自动', value: '自动' }
]

// 座位数选择器
const capacityValue = ref('')
const capacityOptions = [
    { label: '2座', value: '2座' },
    { label: '4座', value: '4座' },
    { label: '5座', value: '5座' },
    { label: '6座', value: '6座' },
    { label: '7座及以上', value: '7座及以上' }
]

// 能源类型选择器
const fuelTypeValue = ref('')
const fuelTypeOptions = [
    { label: '汽油', value: '汽油' },
    { label: '电动', value: '电动' }
]

// 车型选择器
const modelValue = ref('')
const modelOptions = [
    { label: '三厢车', value: '三厢车' },
    { label: '两厢车', value: '两厢车' },
    { label: 'SUV', value: 'SUV' },
    { label: '跑车', value: '跑车' },
    { label: '敞篷车', value: '敞篷车' }
]

// 颜色选择器
const colorValue = ref('')
const colorOptions = [
    { label: '黑色', value: '黑色' },
    { label: '白色', value: '白色' },
    { label: '银灰色', value: '银灰色' },
    { label: '深灰色', value: '深灰色' },
    { label: '红色', value: '红色' },
    { label: '香槟色', value: '香槟色' }
]

async function handleChange(index: string) {
    const resp = await axios.get('/car/getCars', {
        params: {
            brand: brandValue.value,
            price: priceValue.value,
            age: ageValue.value,
            odometer: odometerValue.value,
            displacement: displacementValue.value,
            transmission: transmissionValue.value,
            model: modelValue.value,
            capacity: capacityValue.value,
            fuelType: fuelTypeValue.value,
            color: colorValue.value,
            activeIndex: index,
            searchInfo: searchInfo.value
        }
    })
    car.list = resp.data.data
    total.value = car.list.length
}

function reset() {
    location.reload()
}

//分页相关数据
const currentPage = ref(1)
const pageSize = 12
const total = ref()

// 处理分页变化
const handlePageChange = (newPage: number) => {
    currentPage.value = newPage
}

function handleClick() {
    router.push('/compare')
}
</script>

<template>
    <el-main class="main">
        <el-space alignment="normal" class="container">

            <!-- 搜索框 -->
            <div class="search-wrapper">
                <el-input v-model="searchInfo" class="search" placeholder="搜索" @change="handleChange" clearable>
                </el-input>
            </div>

            <!-- 选择品牌 -->
            <div class="brand-wrapper">
                <el-row>
                    <el-col :span="2">
                        <el-card body-style="padding: 7px;" class="card-left" shadow="never">
                            <el-text class="text">品牌</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="22">
                        <el-card body-style="padding: 0;" style="border-radius: 0;" shadow="never">
                            <el-radio-group v-model="brandValue" size="large" @change="handleChange">
                                <el-radio-button label="不限" />
                                <el-radio-button label="大众" />
                                <el-radio-button label="本田" />
                                <el-radio-button label="宝马" />
                                <el-radio-button label="丰田" />
                                <el-radio-button label="奥迪" />
                                <el-radio-button label="别克" />
                                <el-radio-button label="奔驰" />
                                <el-radio-button label="吉利" />
                                <el-radio-button label="哈弗" />
                                <el-radio-button label="日产" />
                            </el-radio-group>
                        </el-card>
                    </el-col>
                </el-row>
            </div>

            <!-- 选择价格 -->
            <div class="price-wrapper">
                <el-row>
                    <el-col :span="2">
                        <el-card body-style="padding: 7px;" class="card-left" shadow="never">
                            <el-text class="text">价格</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="22">
                        <el-card body-style="padding: 0;" style="border-radius: 0;" shadow="never">
                            <el-radio-group v-model="priceValue" size="large" @change="handleChange">
                                <el-radio-button label="不限" />
                                <el-radio-button label="3万以下" />
                                <el-radio-button label="3-5万" />
                                <el-radio-button label="5-7万" />
                                <el-radio-button label="7-9万" />
                                <el-radio-button label="9-12万" />
                                <el-radio-button label="12-16万" />
                                <el-radio-button label="16-20万" />
                                <el-radio-button label="20万以上" />
                            </el-radio-group>
                        </el-card>
                    </el-col>
                </el-row>
            </div>

            <!-- 选择更多 -->
            <div class="more-wrapper">
                <el-row>
                    <el-col :span="2">
                        <el-card body-style="padding: 33px;" class="card-left" shadow="never">
                            <el-text class="text">其他</el-text>
                        </el-card>
                    </el-col>
                    <el-col :span="22">
                        <el-card body-style="padding: 10px;" style="border-radius: 0;" shadow="never">
                            <el-space wrap>
                                <el-select v-model="ageValue" placeholder="车龄" @change="handleChange" clearable>
                                    <el-option v-for="item in ageOptions" :key="item.value" :label="item.label"
                                        :value="item.value" />
                                </el-select>
                                <el-select v-model="odometerValue" placeholder="里程" @change="handleChange" clearable>
                                    <el-option v-for="item in odometerOptions" :key="item.value" :label="item.label"
                                        :value="item.value" />
                                </el-select>
                                <el-select v-model="displacementValue" placeholder="排量" @change="handleChange" clearable>
                                    <el-option v-for="item in displacementOptions" :key="item.value" :label="item.label"
                                        :value="item.value" />
                                </el-select>
                                <el-select v-model="transmissionValue" placeholder="变速箱" @change="handleChange" clearable>
                                    <el-option v-for="item in transmissionOptions" :key="item.value" :label="item.label"
                                        :value="item.value" />
                                </el-select>
                                <el-select v-model="modelValue" placeholder="车型" @change="handleChange" clearable>
                                    <el-option v-for="item in modelOptions" :key="item.value" :label="item.label"
                                        :value="item.value" />
                                </el-select>
                                <el-select v-model="capacityValue" placeholder="座位数" @change="handleChange" clearable>
                                    <el-option v-for="item in capacityOptions" :key="item.value" :label="item.label"
                                        :value="item.value" />
                                </el-select>
                                <el-select v-model="fuelTypeValue" placeholder="能源类型" @change="handleChange" clearable>
                                    <el-option v-for="item in fuelTypeOptions" :key="item.value" :label="item.label"
                                        :value="item.value" />
                                </el-select>
                                <el-select v-model="colorValue" placeholder="颜色" @change="handleChange" clearable>
                                    <el-option v-for="item in colorOptions" :key="item.value" :label="item.label"
                                        :value="item.value" />
                                </el-select>
                            </el-space>
                        </el-card>
                    </el-col>
                </el-row>
            </div>

            <!-- 找到的车辆数和重置条件 -->
            <div style="margin-top: 20px; display: flex; justify-content: flex-end;">
                <el-text class="count">
                    共为您找到{{ total }}辆车
                </el-text>
                <el-button class="reset-btn" link @click="reset">重置条件</el-button>
            </div>

            <!-- 排序方式 -->
            <el-menu :default-active="activeIndex" class="menuGroup" mode="horizontal" @select="handleChange">
                <el-menu-item index="1">最新上架</el-menu-item>
                <el-menu-item index="2">价格最低</el-menu-item>
                <el-menu-item index="3">价格最高</el-menu-item>
                <el-menu-item index="4">车龄最短</el-menu-item>
                <el-menu-item index="5">里程最少</el-menu-item>
                <el-button type="warning" style="margin: auto 0 auto auto" @click="handleClick">比较</el-button>
            </el-menu>

            <!-- 查询结果 -->
            <el-space alignment="normal" class="result-wrapper" :size="6" wrap>
                <car-card v-for="item in car.list.slice((currentPage - 1) * pageSize, currentPage * pageSize)" :id="item.id"
                    :name="item.name" :price="item.price" :registrationDate="item.registrationDate"
                    :odometer="item.odometer" :image="item.image"></car-card>
            </el-space>

            <!-- 分页栏 -->
            <div v-if="car.list.length != 0" style="display: flex; justify-content: center;">
                <el-pagination class="pagination" background layout="prev, pager, next" :current-page="currentPage"
                    :page-size="pageSize" :total="total" @current-change="handlePageChange" />
            </div>

            <el-empty v-if="car.list.length == 0" description="这里空空如也">
            </el-empty>
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

.search-wrapper {
    display: flex;
    justify-content: flex-end;
}

.search {
    width: 15%;
}

.card-left {
    border-radius: 0;
    background-color: #f7f7f7;
    display: flex;
    justify-content: center;
}

.text {
    font-size: 20px;
    font-weight: bold;
}

.count {
    font-size: 15px;
}

.result-wrapper {
    width: 100%;
    margin-top: 20px;
}

.brand-wrapper {
    margin-top: 10px;
}

.menuGroup {
    margin-top: 20px;
}

.pagination {
    margin-top: 20px;
    margin-bottom: 20px;
}

.reset-btn {
    /* margin-top: 3.5px;
    margin-left: 10px; */
    margin-left: auto;
    font-size: 15px;
}

.reset-btn:hover {
    color: #1E90FF !important;
}
</style>
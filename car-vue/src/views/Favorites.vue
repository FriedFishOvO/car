<script setup lang="ts">
import axios from 'axios';
import { ref, onMounted, reactive } from 'vue'
import CarCard from '../components/car-card.vue'

const props = defineProps({
    id: Number
})

//分页相关数据
const currentPage = ref(1)
const pageSize = 12
const total = ref()

// 处理分页变化
const handlePageChange = (newPage: number) => {
    currentPage.value = newPage
}

const car = reactive({
    list: [] as any
})

onMounted(async () => {
    const resp = await axios.get(`/favorites/${props.id}`)
    car.list = resp.data.data
    total.value = car.list.length
})
</script>

<template>
    <el-main class="main">
        <el-space alignment="normal" direction="vertical" class="container">
            <el-text class="title">我的收藏夹</el-text>

            <el-divider class="divider" direction="horizontal"></el-divider>

            <el-space alignment="normal" class="result-wrapper" :size="6" wrap>
                <car-card class="card" v-for="item in car.list.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                    :id="item.id" :name="item.name" :price="item.price" :registrationDate="item.registrationDate"
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

.title {
    font-size: 30px;
    font-weight: bold;
}

.divider {
    border-width: 2px;
    margin-top: 0px;
}
</style>
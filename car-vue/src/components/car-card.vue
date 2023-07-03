<script setup lang="ts">

defineProps({
    id: Number,
    name: String,
    image: String,
    price: Number,
    registrationDate: String,
    odometer: Number,
    isDeleted: Number
})
</script>

<template>
    <el-link target="_blank" :underline="false" :href="'/car/' + id">
        <el-card class="card" shadow="hover">
            <el-space direction="vertical" alignment="normal" style="width: 100%;">
                <el-image class="image" :src="image"></el-image>
                <el-text class="name" truncated>
                    {{ name }}
                </el-text>
                <el-space direction="horizontal">
                    <el-text>
                        {{ registrationDate?.substring(0, 4) }}年
                    </el-text>
                    <el-divider direction="vertical"></el-divider>
                    <el-text>
                        {{ odometer }}公里
                    </el-text>
                </el-space>
                <el-text class="price">
                    {{ price }}万
                </el-text>
            </el-space>
        </el-card>
    </el-link>
</template>

<style scoped>
.card {
    padding: 0;
    border: 0;
    width: 369.13px;
    height: 362.41px;
}

.image {
    border-radius: 10px;
}

.name {
    font-size: 20px;
}

.price {
    color: red;
    font-size: 20px;
}
</style>
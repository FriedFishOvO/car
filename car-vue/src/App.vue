<script setup lang="ts">
import TheHeader from './components/the-header.vue'
// import TheFooter from './components/the-footer.vue'
</script>

<template>
  <el-container direction="vertical">
    <!-- 头部导航栏 -->
    <the-header></the-header>

    <!-- 中间内容部分 -->
    <router-view></router-view>

    <!-- 底部信息栏 -->
    <!-- <the-footer></the-footer> -->
  </el-container>
</template>

<style scoped></style>

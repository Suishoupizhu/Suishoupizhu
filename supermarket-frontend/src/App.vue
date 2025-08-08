<template>
  <div>
    <nav style="display:flex; gap:12px; padding:12px; border-bottom:1px solid #eee;">
      <router-link to="/">仪表盘</router-link>
      <router-link to="/products">商品</router-link>
      <router-link to="/categories">分类</router-link>
      <span style="margin-left:auto"></span>
      <router-link to="/login">登录</router-link>
    </nav>
    <main style="padding:16px;">
      <router-view />
    </main>
  </div>
</template>
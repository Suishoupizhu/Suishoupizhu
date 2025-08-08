<template>
  <div style="max-width:360px;margin:40px auto;">
    <h2>登录</h2>
    <form @submit.prevent="onSubmit">
      <div>
        <label>用户名</label>
        <input v-model="username" />
      </div>
      <div>
        <label>密码</label>
        <input type="password" v-model="password" />
      </div>
      <button type="submit">登录</button>
    </form>
    <p v-if="error" style="color:red;">{{ error }}</p>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../store/auth'
import api from '../services/api'

const username = ref('admin')
const password = ref('admin123')
const error = ref('')
const router = useRouter()
const store = useAuthStore()

const onSubmit = async () => {
  error.value = ''
  try {
    const { data } = await api.post('/auth/login', { username: username.value, password: password.value })
    store.setToken(data.token)
    router.push('/')
  } catch (e: any) {
    error.value = e?.response?.data?.message || '登录失败'
  }
}
</script>
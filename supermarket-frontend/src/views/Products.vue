<template>
  <div>
    <h2>商品</h2>
    <div style="display:flex; gap:32px;">
      <div style="flex:1;">
        <table border="1" cellspacing="0" cellpadding="6">
          <thead>
            <tr>
              <th>ID</th><th>SKU</th><th>名称</th><th>价格</th><th>库存</th><th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="p in products" :key="p.id">
              <td>{{ p.id }}</td>
              <td>{{ p.sku }}</td>
              <td>{{ p.name }}</td>
              <td>{{ p.price }}</td>
              <td>{{ p.stock }}</td>
              <td><button @click="edit(p)">编辑</button> <button @click="remove(p)">删除</button></td>
            </tr>
          </tbody>
        </table>
      </div>
      <div style="width:360px;">
        <h3>{{ form.id ? '编辑' : '新增' }}商品</h3>
        <form @submit.prevent="save">
          <div>
            <label>SKU</label>
            <input v-model="form.sku" />
          </div>
          <div>
            <label>名称</label>
            <input v-model="form.name" />
          </div>
          <div>
            <label>价格</label>
            <input v-model.number="form.price" type="number" step="0.01" />
          </div>
          <div>
            <label>库存</label>
            <input v-model.number="form.stock" type="number" />
          </div>
          <div>
            <label>描述</label>
            <textarea v-model="form.description" />
          </div>
          <button type="submit">保存</button>
          <button type="button" @click="reset">重置</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '../services/api'

interface Product {
  id?: number
  sku: string
  name: string
  description?: string
  price: number
  stock: number
}

const products = ref<Product[]>([])
const form = ref<Product>({ sku: '', name: '', price: 0, stock: 0 })

const load = async () => {
  const { data } = await api.get('/products')
  products.value = data
}

const save = async () => {
  if (form.value.id) {
    await api.put(`/products/${form.value.id}`, form.value)
  } else {
    await api.post('/products', form.value)
  }
  await load()
  reset()
}

const edit = (p: Product) => {
  form.value = { ...p }
}

const remove = async (p: Product) => {
  await api.delete(`/products/${p.id}`)
  await load()
}

const reset = () => {
  form.value = { sku: '', name: '', price: 0, stock: 0 }
}

onMounted(load)
</script>
<template>
  <div>
    <h2>分类</h2>
    <div style="display:flex; gap:32px;">
      <div style="flex:1;">
        <table border="1" cellspacing="0" cellpadding="6">
          <thead>
            <tr>
              <th>ID</th><th>名称</th><th>描述</th><th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="c in list" :key="c.id">
              <td>{{ c.id }}</td>
              <td>{{ c.name }}</td>
              <td>{{ c.description }}</td>
              <td><button @click="edit(c)">编辑</button> <button @click="remove(c)">删除</button></td>
            </tr>
          </tbody>
        </table>
      </div>
      <div style="width:360px;">
        <h3>{{ form.id ? '编辑' : '新增' }}分类</h3>
        <form @submit.prevent="save">
          <div>
            <label>名称</label>
            <input v-model="form.name" />
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

interface Category { id?: number; name: string; description?: string }

const list = ref<Category[]>([])
const form = ref<Category>({ name: '' })

const load = async () => {
  const { data } = await api.get('/categories')
  list.value = data
}

const save = async () => {
  if (form.value.id) {
    await api.put(`/categories/${form.value.id}`, form.value)
  } else {
    await api.post('/categories', form.value)
  }
  await load()
  reset()
}

const edit = (c: Category) => { form.value = { ...c } }
const remove = async (c: Category) => { await api.delete(`/categories/${c.id}`); await load() }
const reset = () => { form.value = { name: '' } }

onMounted(load)
</script>
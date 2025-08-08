import axios from 'axios'
import { useAuthStore } from '../store/auth'

const api = axios.create({
  baseURL: (import.meta as any).env.VITE_API_BASE || '/api'
})

api.interceptors.request.use((config) => {
  const store = useAuthStore()
  if (store.token) {
    config.headers = config.headers || {}
    config.headers['Authorization'] = `Bearer ${store.token}`
  }
  return config
})

export default api
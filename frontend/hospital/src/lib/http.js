import axios from "axios";

const http = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || "/api",
});

// Example interceptor wiring. You can adapt to your auth flow.
http.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

http.interceptors.response.use(
  (res) => res,
  (error) => {
    // Optionally handle global 401/403, logging, etc.
    return Promise.reject(error);
  }
);

export default http;

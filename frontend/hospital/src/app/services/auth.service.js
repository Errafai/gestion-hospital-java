import api from "./api.service";

export const authService = {
  login: (email, password) => api.post("/auth/login", { email, password }),
  register: (userData) => api.post("/auth/register", userData),
  logout: () => {
    localStorage.removeItem("token");
  },
  getCurrentUser: () => api.get("/auth/me"),
  refreshToken: () => api.post("/auth/refresh"),
};

export default authService;

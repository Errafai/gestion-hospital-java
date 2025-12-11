import api from "./api.service";

export const dossierService = {
  getAll: () => api.get("/dossiers"),
  getById: (id) => api.get(`/dossiers/${id}`),
  create: (dossierData) => api.post("/dossiers", dossierData),
  update: (id, dossierData) => api.put(`/dossiers/${id}`, dossierData),
  delete: (id) => api.delete(`/dossiers/${id}`),
};

export default dossierService;

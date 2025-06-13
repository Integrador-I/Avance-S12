import axios from "axios";
import { Bus, Conductores, Rutas, Viajes } from "./interfacesViajes";

const api = axios.create({
  baseURL: "http://localhost:8080", // Nota el /api añadido
  headers: {
    "Content-Type": "application/json",
  },
  timeout: 10000,
});

// Interceptor para logging
api.interceptors.request.use(config => {
  console.log(`Request to: ${config.url}`);
  return config;
}, error => {
  console.error('Request error:', error);
  return Promise.reject(error);
});

// Funciones API
export const fetchBuses = async (): Promise<Bus[]> => {
  const response = await api.get<Bus[]>("/bus");
  return response.data;
};

export const fetchConductores = async (): Promise<Conductores[]> => {
  const response = await api.get<Conductores[]>("/conductores");
  return response.data;
};

export const fetchRutas = async (): Promise<Rutas[]> => {
  const response = await api.get<Rutas[]>("/rutas");
  return response.data;
};

export const fetchViajes = async (): Promise<Viajes[]> => {
  const response = await api.get<Viajes[]>("/viajes");
  return response.data;
};

export default api;

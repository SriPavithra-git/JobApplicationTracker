import axios from "axios";
import api from "./axios";

export const getAllJobs = () => api.get('api/job/alljobs')

export const createJob = (data) => api.post('api/job/create', data)

export const updateJob =(id, data) => api.put(`api/job/update/${id}`, data)

export const deleteJob= (id) => api.delete(`api/job/delete/${id}`)

export const searchByCompany= (keyword) => api.get(`api/job/search?keyword=${keyword}`)

export const searchByStatus= (status) => api.get(`api/job/status?status=${status}`)
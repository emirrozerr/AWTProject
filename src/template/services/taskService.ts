import axios from 'axios';
import type {  Task, TaskDTO } from '@/types/Task';
// TODO keep axios integration in a shared place for all the services

const API_URL = 'http://localhost:8080/api/tasks';

const getAuthHeaders = () => {
    const token = localStorage.getItem('token');
    return {
      Authorization: `Bearer ${token}`
  };
};


export const taskService = {
    async getAllTasks(): Promise<Task[]> {
        const response = await axios.get<Task[]>(`${API_URL}`, {
            headers: getAuthHeaders(),
        });
        return response.data;
    },
    async getTasksByProjectId(projectId: number): Promise<Task[]> {
      const response = await axios.get<Task[]>(`${API_URL}/project/${projectId}`, {
        headers: getAuthHeaders(),
      });
      return response.data;
    },
    async saveTask(task: TaskDTO): Promise<Task> {
        const response = await axios.post<Task>(`${API_URL}`, task,{
            headers: getAuthHeaders(),
        });
        return response.data;
    },
    async deleteTask(taskId: number): Promise<Task> {
        const response = await axios.delete<Task>(`${API_URL}/${taskId}`,{
            headers: getAuthHeaders(),
        });
        return response.data;
    },
    async updateTask(task: TaskDTO): Promise<Task> {
        const response = await axios.put<Task>(`${API_URL}`,task,{
            headers: getAuthHeaders(),
        });
        return response.data;
    },
};

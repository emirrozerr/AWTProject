import axios from 'axios';
import {type Workspace} from '@/services/workspaceService';
// TODO keep axios integration in a shared place for all the services

const API_URL = 'http://localhost:8080/api/projects';

export interface Project {
    id: number;
    name: string;
    description: string;
    workspace: Workspace;
}
const getAuthHeaders = () => {
    const token = localStorage.getItem('token');
    return {
      Authorization: `Bearer ${token}`
  };
};

export const projectService = {
    async getWorkspaceProjects(workspaceId: number): Promise<Project[]> {
        const response = await axios.get<Project[]>(`${API_URL}/workspace/${workspaceId}`, {
          headers: getAuthHeaders(),
        });
        return response.data;
      }
};

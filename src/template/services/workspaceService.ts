import axios from 'axios';
// TODO keep axios integration in a shared place for all the services

const API_URL = 'http://localhost:8080/api/workspaces';

export interface Workspace {
  id: number;
  name: string;
  description: string;
}

const getAuthHeaders = () => {
    const token = localStorage.getItem('token');
    return {
      headers: {
        Authorization: `Bearer ${token}`
      }
    };
};

export const workspaceService = {
  async getUserWorkspaces(userId: string): Promise<Workspace[]> {
    const response = await axios.get<Workspace[]>(`${API_URL}/user/${userId}`, getAuthHeaders());
    return response.data;
  }

};

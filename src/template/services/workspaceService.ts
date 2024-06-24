import axios from 'axios';
import type {Workspace, WorkspaceDTO} from '@/types/Workspace';
// TODO keep axios integration in a shared place for all the services

const API_URL = 'http://localhost:8080/api/workspaces';

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
    },
    async saveWorkspace(workspace: WorkspaceDTO): Promise<Workspace> {
        const response = await axios.post<Workspace>(`${API_URL}`, workspace, getAuthHeaders());
        return response.data;
    },
    async deleteWorkspaceById(workspaceId: number): Promise<string> {
        const response = await axios.delete<string>(`${API_URL}/${workspaceId}`, getAuthHeaders());
        return response.data;
    },


};

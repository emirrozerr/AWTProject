import { reactive } from 'vue';
import type {Workspace, WorkspaceDTO} from '@/types/Workspace';
import type { Project } from '@/types/Project';
import {workspaceService} from "~/services/workspaceService";
import {projectService} from "~/services/projectService";
import type {TaskDTO} from "~/types/Task";
import {taskService} from "~/services/taskService";

const workspaces = reactive<Workspace[]>([]);
const projects = reactive<Project[]>([]);

const fetchWorkspaces = async () => {
    const userId = localStorage.getItem('userId');
    if (!userId) {
        throw new Error('User ID is not available');
    }
    try {
        const response = await workspaceService.getUserWorkspaces(userId);
        workspaces.splice(0, workspaces.length, ...response); // Reset and populate workspace array
        workspaces.map(async (_workspace: Workspace) => {
            const projectResponse = await projectService.getWorkspaceProjects(_workspace.id);
            projects.splice(0, projects.length, ...projectResponse);
            _workspace.projects = projectResponse
        });
    } catch (error) {
        console.error('Failed to fetch workspaces and/or projects', error);
    }
};

const saveWorkspace = async (workspace: WorkspaceDTO) => {
    try {
        const response = await workspaceService.saveWorkspace(workspace);
        workspaces.push(response);
    } catch (error) {
        console.error('Failed to add task', error);
    }
};


const deleteWorkspace = async (workspaceId: number) => {
    try {
        const response = await workspaceService.deleteWorkspaceById(workspaceId);
        const index = workspaces.findIndex(workspace => workspace.id === workspaceId);
        if (index !== -1) {
            workspaces.splice(index, 1);
        }
    } catch (error) {
        console.error('Failed to delete workspace', error);
    }
}

export function useWorkspace(){
    return{
        fetchWorkspaces,
        deleteWorkspace,
        saveWorkspace,
        workspaces,
        projects
    }
}

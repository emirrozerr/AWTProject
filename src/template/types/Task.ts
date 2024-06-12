import type { Project } from "./Project";

export interface Task {
    id: number;
    title: string;
    description?: string;
    dueDate?: string;
    priority?: string;
    status: string;
    project?: Project;
    projectId?: number // Adjust and maybe separate the request DTO and the model
  }


export interface TaskDTO {
    id?: number;
    title?: string;
    description?: string;
    dueDate?: string;
    priority?: string;
    status?: string;
    project?: Project;
    projectId?: number
}

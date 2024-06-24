import type {Project} from "~/types/Project";

export interface Workspace {
    id: number;
    name: string;
    description: string;
    projects?: Project[];
}

export interface WorkspaceDTO {
    id?: number;
    name?: string;
    description?: string;
    projects?: Project[];
    userId?:string;
}

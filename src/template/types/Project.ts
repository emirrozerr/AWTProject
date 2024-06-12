import type { Workspace } from "./Workspace";

export interface Project {
    id: number;
    name: string;
    description: string;
    workspace: Workspace;
}
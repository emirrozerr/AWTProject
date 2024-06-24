import {BuildingWarehouseIcon, LayoutBoardIcon, LayoutDashboardIcon, MedicineSyrupIcon} from 'vue-tabler-icons';
import {workspaceService} from '@/services/workspaceService';
import type { Workspace } from '@/types/Workspace';
import {type Project, projectService} from '@/services/projectService';

export interface Menu {
    header?: string;
    title?: string;
    icon?: any;
    to?: string;
    chip?: string;
    chipColor?: string;
    chipVariant?: string;
    chipIcon?: string;
    children?: Menu[];
    disabled?: boolean;
    type?: string;
    subCaption?: string;
    collapse?: boolean;
    subItems?: any;
    editButton?:boolean;
}

const staticMenu: Menu[] = [
  { header: 'Home' },
  {
    title: 'Dashboard',
    icon: LayoutDashboardIcon,
    to: '/dashboard'
  },
  {
    title: 'Medication Tracker',
    icon: MedicineSyrupIcon,
    to: '/medicationTracker'
  },
];

export async function getSidebarItems(): Promise<Menu[]> {
    const userId = localStorage.getItem('userId');
    if (!userId) {
      throw new Error('User ID is not available');
    }

    try {
      const workspaces = await workspaceService.getUserWorkspaces(userId);
      const workspaceMenuItems: Menu[] = await Promise.all(workspaces.map(async (workspace: Workspace) => {
        const projects = await projectService.getWorkspaceProjects(workspace.id);
        return {
          title: workspace.name,
          icon: BuildingWarehouseIcon,
          collapse: true,
          subItems: projects.map((project: Project) => ({
            title: project.name,
            icon: LayoutBoardIcon,
            to: `/project/${project.id}`
          }))
        };
      }));


    const workspaceMenuHeader: Menu = { header: 'Workspaces' , editButton: true};
    const workspaceMenu = [workspaceMenuHeader, ...workspaceMenuItems];

    return [...staticMenu, ...workspaceMenu];
    } catch (error) {
        console.error('Failed to fetch workspaces', error);
        return staticMenu; // Return static menu if fetching fails
    }
}

export async function deleteWorkspaceAndRefreshSidebar(workspaceId: number): Promise<Menu[]> {
    try {
        // Delete the workspace
        await workspaceService.deleteWorkspaceById(workspaceId);

        // Refresh the sidebar items
        return await getSidebarItems();
    } catch (error) {
        console.error('Failed to delete workspace and refresh sidebar', error);
        throw error; // Rethrow error to handle it in the calling code
    }
}





import {
    ApertureIcon,
    CopyIcon,
    LayoutDashboardIcon, LoginIcon, MoodHappyIcon, TypographyIcon, UserPlusIcon,BuildingWarehouseIcon,
     LayoutBoardIcon, MedicineSyrupIcon
} from 'vue-tabler-icons';
import {TRUE} from "sass";
import { workspaceService, type Workspace } from '@/services/workspaceService';
import { projectService, type Project } from '@/services/projectService';

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
  
  
    const workspaceMenuHeader: Menu = { header: 'Workspaces' };
    const workspaceMenu = [workspaceMenuHeader, ...workspaceMenuItems];

    return [...staticMenu, ...workspaceMenu];
    } catch (error) {
        console.error('Failed to fetch workspaces', error);
        return staticMenu; // Return static menu if fetching fails
    }
  }

const sidebarItem: Menu[] = [
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
    { header: 'Workspaces' },
    {
        title: 'Personal Workspace',
        icon: BuildingWarehouseIcon,
        collapse: true,
        subItems: [
            {
                title: 'GYM Project',
                icon: LayoutBoardIcon,
                to: '/project'
            },
            {
                title: 'House Renovation Project',
                icon: LayoutBoardIcon,
                to: '/project'
            },
        ],
    },
    {
        title: 'School Workspace',
        icon: BuildingWarehouseIcon,
        collapse: true,
        subItems: [
            {
                title: 'AWT Class',
                icon: LayoutBoardIcon,
                to: '/project'
            },
            {
                title: 'Math Class',
                icon: LayoutBoardIcon,
                to: '/project'
            },
        ],
    },
    { header: 'auth' },
    {
        title: 'Login',
        icon: LoginIcon,
        to: '/auth/login'
    },
    {
        title: 'Register',
        icon: UserPlusIcon,
        to: '/auth/register'
    },
];

export default sidebarItem;

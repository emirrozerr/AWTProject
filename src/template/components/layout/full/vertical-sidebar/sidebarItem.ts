import {
    ApertureIcon,
    CopyIcon,
    LayoutDashboardIcon, LoginIcon, MoodHappyIcon, TypographyIcon, UserPlusIcon,BuildingWarehouseIcon,
     LayoutBoardIcon, MedicineSyrupIcon
} from 'vue-tabler-icons';
import {TRUE} from "sass";

export interface menu {
    header?: string;
    title?: string;
    icon?: any;
    to?: string;
    chip?: string;
    chipColor?: string;
    chipVariant?: string;
    chipIcon?: string;
    children?: menu[];
    disabled?: boolean;
    type?: string;
    subCaption?: string;
    collapse?: boolean;
    subItems?: any;
}

const sidebarItem: menu[] = [
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
        title: 'Workspaces',
        icon: BuildingWarehouseIcon,
        collapse: true,
        subItems: [
            {
                title: 'Personal',
                icon: LayoutBoardIcon,
                to: '/project'
            },
            {
                title: 'Work',
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

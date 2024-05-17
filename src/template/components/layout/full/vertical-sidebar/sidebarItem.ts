import {
    ApertureIcon,
    CopyIcon,
    LayoutDashboardIcon, LoginIcon, MoodHappyIcon, TypographyIcon, UserPlusIcon,BuildingWarehouseIcon, LayoutBoardIcon
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
    { header: 'utilities' },
    {
        title: 'Typography',
        icon: TypographyIcon,
        to: '/ui/typography'
    },
    {
        title: 'Shadow',
        icon: CopyIcon,
        to: '/ui/shadow'
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
    { header: 'Extra' },
    {
        title: 'Icons',
        icon: MoodHappyIcon,
        to: '/icons'
    },
    {
        title: 'Sample Page',
        icon: ApertureIcon,
        to: '/sample-page'
    },
];

export default sidebarItem;

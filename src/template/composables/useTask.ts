import { reactive, computed } from 'vue';

interface Task {
  id: number;
  name: string;
  description?: string;
  dueDate?: string;
  status: 'todo' | 'doing' | 'done';
}

const tasks = reactive<Task[]>([
  { id: 1, name: 'Task 1', description: 'Description 1', dueDate: '2024-06-10', status: 'todo' },
  { id: 2, name: 'Task 2', description: 'Description 2', dueDate: '2024-06-10', status: 'doing' },
  { id: 3, name: 'Task 3', description: 'Description 3', status: 'done' }
]);

const todoTasks = computed(() => tasks.filter(task => task.status === 'todo'));
const doingTasks = computed(() => tasks.filter(task => task.status === 'doing'));
const doneTasks = computed(() => tasks.filter(task => task.status === 'done'));

const updateTaskStatus = (taskId: number, newStatus: 'todo' | 'doing' | 'done') => {
  const task = tasks.find(t => t.id === taskId);
  if (task) {
    task.status = newStatus;
  }
};

const addTaskToList = (name: string, status: 'todo' | 'doing' | 'done', description?:string) => {
  const newTask = {
    id: tasks.length + 1,
    name,
    description,
    dueDate: new Date().toISOString().split('T')[0],
    status
  };
  tasks.push(newTask);
};

export function useTasks() {
  return {
    tasks,
    todoTasks,
    doingTasks,
    doneTasks,
    updateTaskStatus,
    addTaskToList
  };
}

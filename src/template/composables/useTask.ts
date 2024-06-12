import { reactive, computed } from 'vue';
import { taskService} from '@/services/taskService';
import type { Task,TaskDTO } from '@/types/Task';

const tasks = reactive<Task[]>([]);

const fetchTasks = async (projectId: number) => {
  try {
    const response = await taskService.getTasks(projectId);
    tasks.splice(0, tasks.length, ...response); // Reset and populate tasks array
  } catch (error) {
    console.error('Failed to fetch tasks', error);
  }
};

const addTask = async (task: TaskDTO) => {
  try {
    const response = await taskService.saveTask(task);
    tasks.push(response);
  } catch (error) {
    console.error('Failed to add task', error);
  }
};

const updateTask = async (task: TaskDTO) => {
  try {
    const response = await taskService.updateTask(task);
    replaceTask(task.id, response);
  } catch (error) {
    console.error('Failed to update task', error);
  }
}

const deleteTask = async (taskId: number) => {
  try {
    const response = await taskService.deleteTask(taskId);
    removeTask(taskId);
  } catch (error) {
    console.error('Failed to delete task', error);
  }
}

const todoTasks = computed(() => tasks.filter(task => task.status === 'todo'));
const doingTasks = computed(() => tasks.filter(task => task.status === 'doing'));
const doneTasks = computed(() => tasks.filter(task => task.status === 'done'));

const updateTaskStatus = (taskId: number, newStatus: 'todo' | 'doing' | 'done') => {
  const task = tasks.find(t => t.id === taskId);
  if (task) {
    task.status = newStatus;
    const updatedTaskDTO: TaskDTO = {
      id: taskId,
      status: newStatus,
    };
    updateTask(updatedTaskDTO).then(r => console.log(`ID: ${taskId}`,' Task status updated in DB'));
  }
};

const removeTask = (id: number) => {
  const index = tasks.findIndex(task => task.id === id);
  if (index !== -1) {
    tasks.splice(index, 1);
  }
};

function replaceTask(taskId: number | undefined, newTask: Task) {
  const index = tasks.findIndex(task => task.id === taskId);
  if (index !== -1) {
    tasks[index] = newTask;
  } else {
    console.warn(`Task with ID ${taskId} not found.`);
  }
}

export function useTasks() {
  return {
    tasks,
    todoTasks,
    doingTasks,
    doneTasks,
    updateTaskStatus,
    addTask,
    fetchTasks,
    deleteTask,
    updateTask
  };
}

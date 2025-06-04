import { Task } from './types';

export function loadTasks(): Task[] | null {
    try {
        const saved = localStorage.getItem('todoTasks');
        if (saved === null) return null;
        
        const parsed = JSON.parse(saved) as unknown;
        return validateTasks(parsed);
    } catch {
        return null;
    }
}

function isTaskArray(value: unknown): value is Task[] {
    return Array.isArray(value) && value.every(isTask);
}

function isTask(value: unknown): value is Task {
    return typeof value === 'object' && value !== null &&
        'id' in value && typeof value.id === 'number' &&
        'text' in value && typeof value.text === 'string' &&
        'completed' in value && typeof value.completed === 'boolean';
}

function validateTasks(value: unknown): Task[] {
    if (isTaskArray(value)) {
        return value;
    }
    throw new Error('Invalid task data format');
}

export function saveTasks(tasks: Task[]): void {
    localStorage.setItem('todoTasks', JSON.stringify(tasks));
}

export function clearStorage(): void {
    localStorage.removeItem('todoTasks');
}

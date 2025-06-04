import { UIManager } from './uiManager';
import { Task, TaskPriority, TaskDataTuple } from './types'; // Updated imports

// Object type
const config: object = {
    debug: true,
    maxTasks: 100
};

// Never type example
function error(message: string): never {
    throw new Error(message);
}

// Void type example
function log(message: string): void {
    console.log(message);
}

// Any type (should be avoided, but shown for demonstration)
function unsafeParse(json: string): any {
    return JSON.parse(json);
}

// Literal type
function setPriority(priority: 'low' | 'medium' | 'high'): void {
    console.log(`Priority set to ${priority}`);
}

// Union type
function formatTask(task: Task | string): string {
    if (typeof task === 'string') return task;
    return `${task.text} [${task.completed ? '✓' : ' '}]`;
}

document.addEventListener('DOMContentLoaded', () => {
    try {
        new UIManager();
        
        // Demonstration of various types
        const task1: Task = {
            id: 1,
            text: 'Learn TypeScript',
            completed: false,
            priority: TaskPriority.HIGH
        };

        const task2: TaskDataTuple = [2, 'Build Todo App', false, TaskPriority.MEDIUM];

        setPriority('high');
        log(formatTask(task1));
        log(formatTask('Simple string task'));

        // Demonstration of type casting
        const unknownData: unknown = '{"id":3,"text":"Test","completed":false}';
        const parsed = unsafeParse(unknownData as string) as Partial<Task>;
        console.log(parsed);

    } catch (err) {
        error(`Initialization failed: ${err instanceof Error ? err.message : String(err)}`);
    }
});

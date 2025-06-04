import { 
    Task, 
    TaskPriority, 
    TaskManagementAction, 
    TaskDataTuple, 
    TaskOrIdentifier, 
    ImmutableTask,
    TaskID,
    TaskCallback
} from './types';

// Generic Class
export class TaskManager<T extends Task = Task> {
    private tasks: Map<TaskID, T> = new Map();
    private subscribers: Set<TaskCallback<ImmutableTask[]>> = new Set();

    // Type guard
    private isValidTask(value: unknown): value is T {
        return typeof value === 'object' && value !== null && 'id' in value && 'text' in value;
    }

    addTask(task: T | TaskDataTuple): void {
        let newTask: T;
        
        if (Array.isArray(task)) {
            newTask = {
                id: task[0],
                text: task[1],
                completed: task[2],
                priority: task[3]
            } as T;
        } else if (this.isValidTask(task)) {
            newTask = task;
        } else {
            throw new Error('Invalid task type');
        }

        this.tasks.set(newTask.id, newTask);
        this.notifySubscribers();
    }

    getTask<F extends boolean>(id: TaskID, fullObject: F): TaskOrIdentifier<F> | undefined {
        const task = this.tasks.get(id);
        if (!task) return undefined;
        
        return (fullObject ? task : id) as unknown as TaskOrIdentifier<F>;
    }

    processAction(action: TaskManagementAction): void {
        switch (action.type) {
            case 'ADD':
                if (Array.isArray(action.task)) {
                    this.addTask(action.task as unknown as TaskDataTuple);
                } else {
                    this.addTask(action.task as T);
                }
                break;
            case 'DELETE':
                this.deleteTask(action.id);
                break;
            case 'TOGGLE':
                this.toggleTask(action.id);
                break;
            default:
                const _exhaustiveCheck: never = action;
                throw new Error(`Unknown action type: ${_exhaustiveCheck}`);
        }
    }

    deleteTask(id: TaskID): boolean {
        const deleted = this.tasks.delete(id);
        if (deleted) this.notifySubscribers();
        return deleted;
    }

    toggleTask(id: TaskID): void {
        const task = this.tasks.get(id);
        if (task) {
            task.completed = !task.completed;
            this.notifySubscribers();
        }
    }

    subscribe(callback: TaskCallback<ImmutableTask[]>): () => void {
        this.subscribers.add(callback);
        callback(this.getAllTasks());
        return () => this.subscribers.delete(callback);
    }

    private notifySubscribers(): void {
        const tasks = this.getAllTasks();
        this.subscribers.forEach(cb => cb(tasks));
    }

    getAllTasks(): ImmutableTask[] {
        return Array.from(this.tasks.values());
    }
}

export function createTaskFromUnknown(input: unknown): Task {
    if (typeof input === 'string') {
        return {
            id: Date.now(),
            text: input,
            completed: false
        };
    }
    
    const partialTask = input as Partial<Task>;
    return {
        id: partialTask.id ?? Date.now(),
        text: partialTask.text ?? 'New Task',
        completed: partialTask.completed ?? false,
        priority: partialTask.priority
    };
}

// Using let and const
const MAX_TASKS = 100;
let taskCount = 0;

// Using Symbols for private properties
const _id = Symbol('id');
const _text = Symbol('text');
const _completed = Symbol('completed');

// Using Class
export class TodoItem {
    constructor(text, completed = false) { // Default parameter
        this[_id] = Date.now();
        this[_text] = text;
        this[_completed] = completed;
    }

    get id() { return this[_id]; }
    get text() { return this[_text]; }
    get completed() { return this[_completed]; }

    toggle() {
        this[_completed] = !this[_completed];
    }
}

// Using Map for task storage
const taskMap = new Map();

// Using WeakMap for private data
const privateData = new WeakMap();

export class TodoManager {
    constructor() {
        privateData.set(this, {
            subscribers: new Set() // Using Set
        });
    }

    // Using enhanced object literals
    static get priorities() {
        return {
            LOW: Symbol('low'),
            MEDIUM: Symbol('medium'),
            HIGH: Symbol('high')
        };
    }

    // Using rest operator
    addTask(...tasks) {
        if (taskCount + tasks.length > MAX_TASKS) {
            throw new Error(`Maximum of ${MAX_TASKS} tasks allowed`);
        }

        tasks.forEach(task => {
            taskMap.set(task.id, task);
            taskCount++;
        });

        this.notifySubscribers();
    }

    // Using destructuring assignment
    deleteTask({ id }) {
        if (taskMap.delete(id)) {
            taskCount--;
            this.notifySubscribers();
            return true;
        }
        return false;
    }

    // Using for...of loop with iterator
    *getTasks() { // Generator function
        for (const task of taskMap.values()) {
            yield task;
        }
    }

    subscribe(callback) {
        const { subscribers } = privateData.get(this);
        subscribers.add(callback);
        return () => subscribers.delete(callback); // Return unsubscribe function
    }

    notifySubscribers() {
        const { subscribers } = privateData.get(this);
        subscribers.forEach(callback => callback([...taskMap.values()])); // Spread operator
    }
}

// Using Object.assign() to create default settings
export const defaultSettings = Object.assign({
    theme: 'light',
    showCompleted: true
}, JSON.parse(localStorage.getItem('todoSettings')) || {});

// Using Array.from() to convert Map values to array
export const getAllTasks = () => Array.from(taskMap.values());

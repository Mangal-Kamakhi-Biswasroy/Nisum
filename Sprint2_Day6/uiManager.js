import { TodoItem, TodoManager } from './todoManager.js';
import { loadTasks, saveTasks } from './storage.js';

// Using template literals
const createTaskElement = (task) => `
    <li class="taskItem" data-id="${task.id}">
        <input type="checkbox" ${task.completed ? 'checked' : ''}>
        <span class="taskText ${task.completed ? 'completed' : ''}">${task.text}</span>
        <button class="deleteBtn">Delete</button>
    </li>
`;

export class UIManager {
    constructor() {
        this.todoManager = new TodoManager();
        this.taskInput = document.getElementById('taskInput');
        this.addBtn = document.getElementById('addBtn');
        this.taskList = document.getElementById('taskList');

        this.init();
    }

    async init() {
        // Using async/await with Promise
        const savedTasks = await loadTasks();
        const todoItems = savedTasks.map(task => new TodoItem(task.text, task.completed));
        
        this.todoManager.addTask(...todoItems); // Spread operator
        this.renderTasks();

        this.addBtn.addEventListener('click', () => this.addTask());
        this.taskInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') this.addTask();
        });

        this.taskList.addEventListener('click', (e) => {
            if (e.target.classList.contains('deleteBtn')) {
                const taskId = +e.target.closest('.taskItem').dataset.id;
                this.deleteTask(taskId);
            } else if (e.target.type === 'checkbox') {
                const taskId = +e.target.closest('.taskItem').dataset.id;
                this.toggleTask(taskId);
            }
        });

        // Subscribe to task changes
        this.unsubscribe = this.todoManager.subscribe(() => {
            this.renderTasks();
            saveTasks(getAllTasks());
        });
    }

    addTask() {
        const text = this.taskInput.value.trim();
        if (text) {
            const newTask = new TodoItem(text);
            this.todoManager.addTask(newTask);
            this.taskInput.value = '';
        }
    }

    deleteTask(taskId) {
        const task = Array.from(this.todoManager.getTasks()).find(t => t.id === taskId);
        if (task) {
            this.todoManager.deleteTask(task);
        }
    }

    toggleTask(taskId) {
        const task = Array.from(this.todoManager.getTasks()).find(t => t.id === taskId);
        if (task) {
            task.toggle();
            this.todoManager.notifySubscribers();
        }
    }

    renderTasks() {
        this.taskList.innerHTML = '';
        for (const task of this.todoManager.getTasks()) { // Using iterator
            this.taskList.insertAdjacentHTML('beforeend', createTaskElement(task));
        }
    }
}

import { TaskManager } from './todoManager';
import { loadTasks, saveTasks } from './storage';
import { Task, TaskPriority } from './types';

export class UIManager {
    private taskManager = new TaskManager();
    private taskInput: HTMLInputElement;
    private addBtn: HTMLButtonElement;
    private taskList: HTMLUListElement;
    private unsubscribe: () => void = () => {};

    constructor() {
        this.taskInput = document.getElementById('taskInput') as HTMLInputElement;
        this.addBtn = document.getElementById('addBtn') as HTMLButtonElement;
        this.taskList = document.getElementById('taskList') as HTMLUListElement;

        this.initialize();
    }

    private async initialize(): Promise<void> {
        const savedTasks = await new Promise<Task[] | null>((resolve) => {
            const tasks = loadTasks();
            resolve(tasks);
        });

        if (savedTasks) {
            savedTasks.forEach(task => this.taskManager.addTask(task));
        }

        this.setupEventListeners();
        this.unsubscribe = this.taskManager.subscribe(tasks => {
            this.renderTasks(tasks);
            saveTasks(tasks);
        });
    }

    private setupEventListeners(): void {
        this.addBtn.addEventListener('click', () => this.addTask());
        this.taskInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') this.addTask();
        });

        this.taskList.addEventListener('click', (e) => {
            const target = e.target as HTMLElement;
            const listItem = target.closest('.taskItem') as HTMLLIElement | null;
            
            if (!listItem) return;
            
            const taskId = Number(listItem.dataset.id);
            
            if (target.classList.contains('deleteBtn')) {
                this.taskManager.deleteTask(taskId);
            } else if (target.tagName === 'INPUT' && target.getAttribute('type') === 'checkbox') {
                this.taskManager.toggleTask(taskId);
            }
        });
    }

    private renderTasks(tasks: readonly Task[]): void {
        this.taskList.innerHTML = '';
        tasks.forEach(task => {
            const li = document.createElement('li');
            li.className = 'taskItem';
            li.dataset.id = task.id.toString();

            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.checked = task.completed;

            const span = document.createElement('span');
            span.className = `taskText ${task.completed ? 'completed' : ''}`;
            span.textContent = task.text;

            const deleteBtn = document.createElement('button');
            deleteBtn.className = 'deleteBtn';
            deleteBtn.textContent = 'Delete';

            li.append(checkbox, span, deleteBtn);
            this.taskList.appendChild(li);
        });
    }

    private addTask(): void {
        const text = this.taskInput.value.trim();
        if (text) {
            const newTask: Task = {
                id: Date.now(),
                text,
                completed: false,
                priority: TaskPriority.MEDIUM
            };
            this.taskManager.addTask(newTask);
            this.taskInput.value = '';
        }
    }
}

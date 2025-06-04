// Using Promise for async operations
export const loadTasks = () => {
    return new Promise((resolve) => {
        const savedTasks = JSON.parse(localStorage.getItem('todoTasks') || '[]');
        resolve(savedTasks);
    });
};

export const saveTasks = (tasks) => {
    return new Promise((resolve) => {
        localStorage.setItem('todoTasks', JSON.stringify(tasks));
        resolve(true);
    });
};

// Using Object.is() for comparison
export const compareTasks = (task1, task2) => {
    return Object.is(task1.id, task2.id) && 
           Object.is(task1.text, task2.text) && 
           Object.is(task1.completed, task2.completed);
};

import { UIManager } from './uiManager.js';

// Using Array.of() for demonstration
const demoArray = Array.of(1, 2, 3);
console.log('Demo Array.of():', demoArray);

// Using WeakSet for tracking DOM elements
const renderedElements = new WeakSet();

document.addEventListener('DOMContentLoaded', () => {
    new UIManager();
});

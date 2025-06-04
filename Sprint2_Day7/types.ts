// Basic Types
export type TaskID = number;
export type TaskCallback<T> = (value: T) => void;

// Enum
export enum TaskPriority {
    LOW = 'low',
    MEDIUM = 'medium',
    HIGH = 'high'
}

// Tuple
export type TaskDataTuple = [TaskID, string, boolean, TaskPriority?];

// Discriminated Union
export type TaskManagementAction =
    | { type: 'ADD'; task: Task }
    | { type: 'DELETE'; id: TaskID }
    | { type: 'TOGGLE'; id: TaskID };

// Interface with optional properties
export interface Task {
    id: TaskID;
    text: string;
    completed: boolean;
    priority?: TaskPriority;
    createdAt?: Date;
}

// Mapped Type
export type ImmutableTask = Readonly<Task>;

// Conditional Type
export type TaskOrIdentifier<F extends boolean> = F extends true ? Task : TaskID;

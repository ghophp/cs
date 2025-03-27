package worker_pool

type WorkerPool interface {
	AddTask(task func())
	Start()
	Stop()
}

package app.service

import app.model.Info
import java.lang.management.ManagementFactory
import java.util.concurrent.ForkJoinPool

object Informer {
	fun info(): Info {
		val runtime = Runtime.getRuntime()
		val javaVersion = System.getProperty("java.version")
		val processors = runtime.availableProcessors()
		val parallelism = ForkJoinPool.commonPool().parallelism
		val totalMemory = runtime.totalMemory()
		val maxMemory = runtime.maxMemory()
		val jvmArgs = jvmArgs()

		return Info(
			javaVersion,
			processors,
			parallelism,
			totalMemory,
			maxMemory,
			jvmArgs
		)
	}

	private fun jvmArgs(): String {
		val runtimeMxBean = ManagementFactory.getRuntimeMXBean()
		return runtimeMxBean.inputArguments.joinToString(" ")
	}
}

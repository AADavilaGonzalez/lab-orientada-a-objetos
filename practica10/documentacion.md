# Documentación de Estrategias de Sincronización

## 1. Sincronización en el acceso a cuentas bancarias

- **Métodos sincronizados con synchronized**:  
  Las operaciones de depósito y retiro de `CuentaBancaria` están protegidas
  mediante métodos sincronizados. Esto garantiza que solo un hilo pueda modificar el
  saldo de una cuenta a la vez, evitando race conditions.

## 2. Productor-Consumidor con Buffer Compartido

- **Buffer compartido sincronizado**:  
  La clase `BufferCompartido` implementa un buffer de tamaño fijo para la comunicación
  entre productores (clientes) y consumidores (cajeros).
- **Uso de `wait()` y `notifyAll()`**:  
  Los métodos `agregar` y `retirar` están sincronizados utilizando `wait()` para
  suspender hilos cuando el buffer está lleno o vacío, respectivamente.  
  Cuando se agrega o retira un elemento se llama a `notifyAll()` para despertar
  a los hilos que esten esperando.
- **Bandera de finalización**:  
  Se utiliza una bandera `produccionFinalizada` para indicar a los consumidores
  que no habrá más elementos, permitiendo que los cajeros terminen correctamente.

## 3. Manejo de múltiples hilos

- **Clientes y cajeros**:  
  Los clientes depositan dinero y agregan montos al buffer.  
  Los cajeros retiran montos del buffer y de las cuentas bancarias.
- **Sincronización en la simulación**:  
  El método principal espera la finalización de todos los hilos usando `join()`.

## 4. Uso de ThreadPool (ExecutorService)

- **ThreadPoolDavila**:  
  Se implementó un pool de hilos con `ExecutorService` para gestionar la ejecución
  de tareas paralelas, facilitando la administración de recursos y de hilos.

## 5. Prevención de condiciones de carrera y deadlocks

- **Diseño cuidadoso de bloqueos**:  
  El uso de métodos sincronizados y la correcta señalización con `wait()`/`notifyAll()`
  previenen condiciones de carrera y deadlocks en el acceso al buffer.

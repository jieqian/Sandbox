package com.sandbox.sizeof.test;

/**
 * Since Java purposefully hides many aspects of memory management, discovering 
 * how much memory your objects consume takes some work. You could use the
 * Runtime.freeMemory() method to measure heap size differences before and after 
 * several objects have been allocated. Several articles, such as Ramchander 
 * Varadarajan's "Question of the Week No. 107" (Sun Microsystems, September 2000) 
 * and Tony Sintes's "Memory Matters" (JavaWorld, December 2001), detail that idea. 
 * Unfortunately, the former article's solution fails because the implementation 
 * employs a wrong Runtime method, while the latter article's solution has its own 
 * imperfections:
 * 
 *      1. A single call to Runtime.freeMemory() proves insufficient because a JVM may 
 *      decide to increase its current heap size at any time (especially when it runs garbage 
 *      collection). Unless the total heap size is already at the -Xmx maximum size, we 
 *      should use Runtime.totalMemory()-Runtime.freeMemory() as the used heap size.
 *      
 *      2. Executing a single Runtime.gc() call may not prove sufficiently aggressive for 
 *      requesting garbage collection. We could, for example, request object finalizers to run 
 *      as well. And since Runtime.gc() is not documented to block until collection 
 *      completes, it is a good idea to wait until the perceived heap size stabilizes.
 *      
 *      3. If the profiled class creates any static data as part of its per-class class 
 *      initialization (including static class and field initializers), the heap memory used for 
 *      the first class instance may include that data. We should ignore heap space 
 *      consumed by the first class instance.
 * 
 *  Considering those problems, I present Sizeof, a tool with which I snoop at various 
 *  Java core and application classes:
 *  
 *  Sizeof's key methods are runGC() and usedMemory(). I use a runGC() wrapper 
 *  method to call _runGC() several times because it appears to make the method more 
 *  aggressive. (I am not sure why, but it's possible creating and destroying a method 
 *  call-stack frame causes a change in the reachability root set and prompts the 
 *  garbage collector to work harder. Moreover, consuming a large fraction of the heap 
 *  space to create enough work for the garbage collector to kick in also helps. In 
 *  general, it is hard to ensure everything is collected. The exact details depend on the 
 *  JVM and garbage collection algorithm.)
 *  Note carefully the places where I invoke runGC(). You can edit the code between the
 *  heap1 and heap2 declarations to instantiate anything of interest.
 *  
 *  Also note how Sizeof prints the object size: the transitive closure of data required by 
 *  all count class instances, divided by count. For most classes, the result will be 
 *  memory consumed by a single class instance, including all of its owned fields. That 
 *  memory footprint value differs from data provided by many commercial profilers that 
 *  report shallow memory footprints (for example, if an object has an int[] field, its 
 *  memory consumption will appear separately).
 * */

public class Sizeof
{
    public static void main (String [] args) throws Exception
    {
        // Warm up all classes/methods we will use
        runGC ();
        usedMemory ();
        // Array to keep strong references to allocated objects
        final int count = 100000;
        Object [] objects = new Object [count];
        
        long heap1 = 0;
        // Allocate count+1 objects, discard the first one
        for (int i = -1; i < count; ++ i)
        {
            Object object = null;
            
            // Instantiate your data here and assign it to object
            
//            object = new Object ();
            //object = new Integer (i);
//            object = new Long (i);
            //object = new String ();
            //object = new byte [128][1];
            
            if (i >= 0)
                objects [i] = object;
            else
            {
                object = null; // Discard the warm up object
                runGC ();
                heap1 = usedMemory (); // Take a before heap snapshot
            }
        }
        runGC ();
        long heap2 = usedMemory (); // Take an after heap snapshot:
        
        final int size = Math.round (((float)(heap2 - heap1))/count);
        System.out.println ("'before' heap: " + heap1 +
                            ", 'after' heap: " + heap2);
        System.out.println ("heap delta: " + (heap2 - heap1) +
            ", {" + objects [0].getClass () + "} size = " + size + " bytes");
        for (int i = 0; i < count; ++ i) objects [i] = null;
        objects = null;
    }
    private static void runGC () throws Exception
    {
        // It helps to call Runtime.gc()
        // using several method calls:
        for (int r = 0; r < 4; ++ r) _runGC ();
    }
    private static void _runGC () throws Exception
    {
        long usedMem1 = usedMemory (), usedMem2 = Long.MAX_VALUE;
        for (int i = 0; (usedMem1 < usedMem2) && (i < 500); ++ i)
        {
            s_runtime.runFinalization ();
            s_runtime.gc ();
            Thread.currentThread ().yield ();
            
            usedMem2 = usedMem1;
            usedMem1 = usedMemory ();
        }
    }
    private static long usedMemory ()
    {
        return s_runtime.totalMemory () - s_runtime.freeMemory ();
    }
    
    private static final Runtime s_runtime = Runtime.getRuntime ();
} // End of class

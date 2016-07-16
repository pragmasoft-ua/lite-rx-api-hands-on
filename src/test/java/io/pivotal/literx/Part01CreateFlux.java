package io.pivotal.literx;

import java.util.Arrays;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.test.TestSubscriber;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="http://projectreactor.io/core/docs/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 * @see <a href="http://projectreactor.io/core/docs/api/reactor/core/test/TestSubscriber.html">TestSubscriber Javadoc</a>
 */
public class Part01CreateFlux {

//========================================================================================

	@Test
	public void empty() {
		Flux<String> flux = emptyFlux();
		TestSubscriber
				.subscribe(flux)
				.assertValueCount(0)
				.assertComplete();
	}

	// TODO Return an empty Flux
	Flux<String> emptyFlux() {
		return Flux.empty();
	}

//========================================================================================

	@Test
	public void fromValues() {
		Flux<String> flux = fooBarFluxFromValues();
		TestSubscriber
				.subscribe(flux)
				.assertValues("foo", "bar")
				.assertComplete();
	}

	// TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
	Flux<String> fooBarFluxFromValues() {
		return Flux.just("foo", "bar");
	}

//========================================================================================

	@Test
	public void fromList() {
		Flux<String> flux = fooBarFluxFromList();
		TestSubscriber
				.subscribe(flux)
				.assertValues("foo", "bar")
				.assertComplete();
	}

	// TODO Create a Flux from a List that contains 2 values "foo" and "bar"
	Flux<String> fooBarFluxFromList() {
		return Flux.fromIterable(Arrays.asList("foo", "bar"));
	}

//========================================================================================

	@Test
	public void error() {
		Flux<String> flux = errorFlux();
		TestSubscriber
				.subscribe(flux)
				.assertError(IllegalStateException.class)
				.assertNotComplete();
	}

	// TODO Create a Flux that emits an IllegalStateException
	Flux<String> errorFlux() {
		return Flux.error(new IllegalStateException());
	}

//========================================================================================

	@Test
	public void neverTerminates() {
		Flux<String> flux = neverTerminatedFlux();
		TestSubscriber
				.subscribe(flux)
				.assertNotTerminated();
	}

	// TODO Create a Flux that never terminates
	Flux<String> neverTerminatedFlux() {
		return Flux.interval(1000).map(String::valueOf);
	}

//========================================================================================

	@Test
	public void countEachSecond() {
		Flux<Long> flux = counter();
		TestSubscriber
				.subscribe(flux)
				.assertNotTerminated()
				.awaitAndAssertNextValues(0L, 1L, 2L);
	}

	// TODO Create a Flux that emits an increasing value each 100ms
	Flux<Long> counter() {
		return Flux.interval(100);
	}

}

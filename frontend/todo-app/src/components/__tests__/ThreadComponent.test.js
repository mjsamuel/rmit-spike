import React from 'react'
import ReactTestUtils from 'react-dom/test-utils'; 
import { shallow, mount } from 'enzyme'
import ThreadComponent from '../todo/ThreadComponent'


describe('ThreadComponent', () => {
	var component, thread;

	beforeEach(() => {
		thread = {
			id: 1,
			author: "John Smith",
			title: "The Rise and Fall of SEPT at RMIT",
			primary_channel: "sept"
		}
	})
	beforeEach(() => {
		component = mount(<ThreadComponent {...thread} />)
	})

	it('should exist', () => {
		expect(component).toBeTruthy();
		expect(component.props().id).toEqual(thread.id)
		expect(component.props().author).toEqual(thread.author)
		expect(component.props().title).toEqual(thread.title)
		expect(component.props().primary_channel).toEqual(thread.primary_channel)

	})

	it('display primary channel when props are passed', () => {
		let channel = component.find('.channel');
		expect(channel.text()).toEqual("c/" + thread.primary_channel);
	})

	it('display thread title when props are passed', () => {
		let title = component.find('.thread-title');
		expect(title.text()).toEqual(thread.title);
	})

	/**
	 * Functionality not implemented yet; API not functional
	 */
	// it('display error when props not passed', () => {
	// 	component = mount(<ThreadComponent />)
	// 	let node = component.find('.error');
	// 	expect(node.text()).toEqual('Sorry. Thread doesn\'t exist.');
	// })

})
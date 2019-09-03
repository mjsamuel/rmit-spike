import React from 'react'
import ReactTestUtils from 'react-dom/test-utils'; 
import { shallow, mount } from 'enzyme'
import CommentComponent from '../todo/CommentComponent'


describe('CommentComponent', () => {
	var component, comment;

	beforeEach(() => {
		comment = {
            "id": 4,
            "author": "The Syrian",
            "spikes": 6,
            "timeNumber": 2,
            "timeUnit": "days",
            "content": "I tire of these games Batthiatus, I would see them end."
		}
	})
	beforeEach(() => {
		component = mount(<CommentComponent {...comment} />)
	})

	it('should exist', () => {
		expect(component).toBeTruthy();
		expect(component.props().id).toEqual(comment.id)
		expect(component.props().spikes).toEqual(comment.spikes)
		expect(component.props().timeNumber).toEqual(comment.timeNumber)
		expect(component.props().timeUnit).toEqual(comment.timeUnit)
		expect(component.props().content).toEqual(comment.content)

	})

	it('throws error when not given props', () => {
		// Not implemented
		// component = mount(<CommentComponent />)
		// let error = component.find('.error')
		// expect(error.text()).toEqual("Something went wrong.");
	})

	it('renders author name', () => {
		let author = component.find('.author')
		expect(author.text()).toEqual("u/" + comment.author);
	})

	it('renders number of spikes', () => {
		let spikes = component.find('.spikes')
		expect(spikes.text()).toEqual(comment.spikes + " Spikes");
	})

	it('renders timeDelta correctly', () => {
		let timeDelta = component.find('.timeDelta')
		expect(timeDelta.text()).toEqual(comment.timeNumber + " "  + comment.timeUnit + " ago");
	})

	it('renders comment content correctly', () => {
		let content = component.find('.content')
		expect(content.text()).toEqual(comment.content);
	})

	it('upspike activation changes state.upspiked to true, state.downspiked to false', () => {
		/**
		 * TODO: Implement this
		 * Currently not sure how to test a function within a component. When time permits, research:
		 * https://stackoverflow.com/questions/25533036/test-a-react-component-function-with-jest
		 */

		 //intentional fail:
		 // expect(component).toBeUndefined()
	})

	it('downspike activation changes state.downspiked to true, state.upspiked to false', () => {
		/**
		 * TODO: Implement this
		 * Currently not sure how to test a function within a component. When time permits, research:
		 * https://stackoverflow.com/questions/25533036/test-a-react-component-function-with-jest
		 */

 		 //intentional fail:
		 // expect(component).toBeUndefined()
	})

	it('reply activation sets state.replyActive to true, state.reportActive to false', () => {
		/**
		 * TODO: Implement this
		 * Currently not sure how to test a function within a component. When time permits, research:
		 * https://stackoverflow.com/questions/25533036/test-a-react-component-function-with-jest
		 */

 		 //intentional fail:
		 // expect(component).toBeUndefined()
	})

	it('report activation sets state.reportActive to true, state to replyActive to false', () => {
		/**
		 * TODO: Implement this
		 * Currently not sure how to test a function within a component. When time permits, research:
		 * https://stackoverflow.com/questions/25533036/test-a-react-component-function-with-jest
		 */

 		 //intentional fail:
		 // expect(component).toBeUndefined()
	})
})
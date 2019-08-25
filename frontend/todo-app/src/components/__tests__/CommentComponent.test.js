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

	// it('display primary channel when props are passed', () => {
	// 	let channel = component.find('.channel');
	// 	expect(channel.text()).toEqual("c/" + thread.primary_channel);
	// })

	// it('display thread title when props are passed', () => {
	// 	let title = component.find('.thread-title');
	// 	expect(title.text()).toEqual(thread.title);
	// })
})
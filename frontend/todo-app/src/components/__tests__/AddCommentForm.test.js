import React from 'react'
import ReactTestUtils from 'react-dom/test-utils'; 
import { shallow, mount } from 'enzyme'
import AddCommentForm from '../todo/AddCommentForm'


describe('AddCommentForm', () => {
	var component, form;

	beforeEach(() => {
		form = {
			thread_id: 1,
			isReply: false,
			isReport: true,
		}
	})
	beforeEach(() => {
		component = mount(<AddCommentForm {...form} />)
	})

	it('should exist', () => {
		expect(component).toBeTruthy();
		expect(component.props().thread_id).toEqual(form.thread_id)
		expect(component.props().isReply).toEqual(form.isReply)
		expect(component.props().isReport).toEqual(form.isReport)
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
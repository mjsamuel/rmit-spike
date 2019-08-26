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

	it('AddCommentForm should exist', () => {
		expect(component).toBeTruthy();
		expect(component.props().thread_id).toEqual(form.thread_id)
		expect(component.props().isReply).toEqual(form.isReply)
		expect(component.props().isReport).toEqual(form.isReport)
	})

	it('AddCommentForm state initialised to null', () => {
		// console.log(component.state())
		expect(component.state().value).toEqual('')
	})

	it('AddCommentForm report placeholder', () => {
		const report_phrase = "What would you like to report? Please include as much detail as possible"
		expect(component.state().placeholder).toBeDefined()
		expect(component.state().placeholder).toEqual(report_phrase)
	})

	it('AddCommentForm report placeholder', () => {
		const reply_form = {
			thread_id: 1,
			isReply: true,
			isReport: false,
		}
		const component = mount(<AddCommentForm {...reply_form} />)
		const reply_phrase = "What are your thoughts?"
		expect(component.state().placeholder).toBeDefined()
		expect(component.state().placeholder).toEqual(reply_phrase)
	})
})
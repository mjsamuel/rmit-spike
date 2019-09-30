import React from 'react'
import ReactTestUtils from 'react-dom/test-utils'; 
import { shallow, mount } from 'enzyme'
import InteractionEntryForm from '../InteractionEntryForm'


describe('InteractionEntryForm', () => {
	var component, form;

	beforeEach(() => {
		form = {
			thread_id: 1,
			isReply: false,
			isReport: true,
		}
	})
	beforeEach(() => {
		component = mount(<InteractionEntryForm {...form} />)
	})

	it('InteractionEntryForm should exist', () => {
		expect(component).toBeTruthy();
		expect(component.props().thread_id).toEqual(form.thread_id)
		expect(component.props().isReply).toEqual(form.isReply)
		expect(component.props().isReport).toEqual(form.isReport)
	})

	it('InteractionEntryForm state initialised to null', () => {
		// console.log(component.state())
		expect(component.state().value).toEqual('')
	})

	it('InteractionEntryForm report placeholder', () => {
		const report_phrase = "What would you like to report? Please include as much detail as possible"
		expect(component.state().placeholder).toBeDefined()
		expect(component.state().placeholder).toEqual(report_phrase)
	})

	it('InteractionEntryForm report placeholder', () => {
		const reply_form = {
			thread_id: 1,
			isReply: true,
			isReport: false,
		}
		const component = mount(<InteractionEntryForm {...reply_form} />)
		const reply_phrase = "What are your thoughts?"
		expect(component.state().placeholder).toBeDefined()
		expect(component.state().placeholder).toEqual(reply_phrase)
	})
})
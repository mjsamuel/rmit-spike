import React, { Component } from 'react'
import ThreadDataService from '../../api/todo/ThreadDataService.js'

class AddCommentForm extends Component {
	constructor(props) {
		super(props);
		this.state = {
			value: '',
			placeholder: this.props.isReport ? "What would you like to report? Please include as much detail as possible" : "What are your thoughts?"
		};
		console.log(this.props)
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}


	handleChange(event) {
		this.setState({
			value: event.target.value
		});
	}

	handleSubmit(event) {
		const request = {
			content: this.state.value,
			datetime: Date.now(),
			author_id: 1, //Needs to reflect current user
			thread_id: this.props.thread_id,
			reply_id: this.props.isReply ? this.props.reply_id : null,

		}

		const apiCall = this.props.isReport ? ThreadDataService.addReport : ThreadDataService.addComment
		console.log(apiCall)
		apiCall(this.props.thread_id, request);
		this.props.updateParent()
		this.setState({value: ''})
		event.preventDefault();
	}

	render() {
		return (
			<form onSubmit={this.handleSubmit}>
        		<textarea rows="4" placeholder={this.state.placeholder} value={this.state.value} onChange={this.handleChange}></textarea>
        		<input type="submit" className="btn btn-success" value="Submit" />
        	</form>
		);
	}

}
export default AddCommentForm
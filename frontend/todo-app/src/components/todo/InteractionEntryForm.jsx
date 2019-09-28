import React, { Component } from 'react'
import ThreadDataService from '../../api/todo/ThreadDataService.js'


/**
 * InteractionEntryForm is a form that presents a text entry box for the user to submit
 * a comment or report a thread or comment.
 * It makes use of the following props:
 * @param thread_id: id of the parent thread
 * @param isReply: boolean indicating if the comment is in reply to another comment
 * @param isReport: boolean indicating if the entry is a report
 * @param updateParent: function handle to the function that refreshes the parent thread
 */
class InteractionEntryForm extends Component {
	constructor(props) {
		super(props);
		this.state = {
			value: '',
			placeholder: this.props.isReport ? "What would you like to report? Please include as much detail as possible" : "What are your thoughts?"
		};
		// console.log(this.props)
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}


	/**
	 * Update the DOM as the user enters text
	 */
	handleChange(event) {
		this.setState({
			value: event.target.value
		});
	}

	/**
	 * Construct a request to be sent to the relevant API when user selects submit
	 * then refresh the thread and update the state of the text box to empty
	 */
	handleSubmit(event) {
		const request = {
			content: this.state.value,
			datetime: Date.now(),
			userId: 1, //Placeholder: Needs to reflect current user when auth implemented
			threadId: this.props.thread_id,
			replyId: this.props.isReply ? this.props.reply_id : null
		}

		const apiCall = this.props.isReport ? ThreadDataService.addReport : ThreadDataService.addComment
		// console.log(apiCall)
		apiCall(this.props.thread_id, request)
		.then((response) => {
			console.log(response)
		})
		.catch((error) => {
			console.log(error);
		})
		this.props.updateParent()
		this.setState({value: ''})
		event.preventDefault();
	}

	/**
	 * Render text box
	 */
	render() {
		return (
			<form onSubmit={this.handleSubmit}>
        		<textarea rows="4" placeholder={this.state.placeholder} value={this.state.value} onChange={this.handleChange}></textarea>
        		<input type="submit" className="btn btn-success" value="Submit" />
        	</form>
		);
	}

}
export default InteractionEntryForm
import React from 'react'

class FooterComponent extends React.Component {
    render() {
        return (
            <>
                <footer className="footer">
                    <span className="text-muted">All Rights Reserved 2019 @RMIT SEPT</span>
                </footer>

                <style jsx="true">{`
                .footer {
                    position: absolute;
                    right: 0;
                    bottom: 0;
                    left: 0;
                    }
                `}</style>
            </>
        )
    }
}

export default FooterComponent
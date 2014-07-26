#include "qt/view/QtCodeView.h"

#include <QFrame>
#include <QScrollArea>
#include <QVBoxLayout>

#include "data/location/TokenLocationFile.h"
#include "qt/element/QtCodeFile.h"
#include "qt/utility/utilityQt.h"
#include "qt/view/QtViewWidgetWrapper.h"
#include "utility/FileSystem.h"
#include "utility/messaging/type/MessageActivateToken.h"
#include "utility/text/TextAccess.h"

QtCodeView::QtCodeView(ViewLayout* viewLayout)
	: CodeView(viewLayout)
	, m_refreshViewFunctor(std::bind(&QtCodeView::doRefreshView, this))
	, m_clearCodeSnippetsFunctor(std::bind(&QtCodeView::doClearCodeSnippets, this))
	, m_addCodeSnippetFunctor(std::bind(&QtCodeView::doAddCodeSnippet, this, std::placeholders::_1))
{
}

QtCodeView::~QtCodeView()
{
}

void QtCodeView::createWidgetWrapper()
{
	setWidgetWrapper(std::make_shared<QtViewWidgetWrapper>(std::make_shared<QScrollArea>()));
}

void QtCodeView::initView()
{
	QWidget* widget = QtViewWidgetWrapper::getWidgetOfView(this);
	widget->setObjectName("code_view");

	QScrollArea* scroll = dynamic_cast<QScrollArea*>(widget);
	m_frame = std::make_shared<QFrame>(scroll);

	QVBoxLayout* layout = new QVBoxLayout(m_frame.get());
	layout->setSpacing(10);
	layout->setContentsMargins(15, 15, 15, 15);
	layout->setAlignment(Qt::AlignTop);
	m_frame->setLayout(layout);

	scroll->setWidgetResizable(true);
	scroll->setWidget(m_frame.get());

	setStyleSheet();
}

void QtCodeView::refreshView()
{
	m_refreshViewFunctor();
}

void QtCodeView::addCodeSnippet(const CodeSnippetParams params)
{
	m_addCodeSnippetFunctor(params);
}

void QtCodeView::clearCodeSnippets()
{
	m_clearCodeSnippetsFunctor();
}

void QtCodeView::activateToken(Id tokenId) const
{
	MessageActivateToken message(tokenId);
	message.dispatch();
}

void QtCodeView::doRefreshView()
{
	setStyleSheet();
}

void QtCodeView::doAddCodeSnippet(const CodeSnippetParams params)
{
	std::string fileName = FileSystem::fileName(params.locationFile.getFilePath());
	QtCodeFile* file = nullptr;

	for (std::shared_ptr<QtCodeFile> filePtr : m_files)
	{
		if (filePtr->getFileName() == fileName)
		{
			file = filePtr.get();
			break;
		}
	}

	if (!file)
	{
		QWidget* widget = QtViewWidgetWrapper::getWidgetOfView(this);
		std::shared_ptr<QtCodeFile> filePtr = std::make_shared<QtCodeFile>(this, fileName, widget);

		m_files.push_back(filePtr);
		file = filePtr.get();
		m_frame->layout()->addWidget(file);
	}

	file->addCodeSnippet(params.startLineNumber, params.code, params.locationFile, params.activeTokenIds);
}

void QtCodeView::doClearCodeSnippets()
{
	m_files.clear();
}

void QtCodeView::setStyleSheet()
{
	QWidget* widget = QtViewWidgetWrapper::getWidgetOfView(this);
	widget->setStyleSheet(TextAccess::createFromFile("data/gui/code_view/code_view.css")->getText().c_str());
}

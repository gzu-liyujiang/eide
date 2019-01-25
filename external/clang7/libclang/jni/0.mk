

clang_lib_analysis_DIR=$(CLANG_ROOT_PATH)/lib/Analysis

clang_lib_analysis_SRC_FILES:= \
  $(clang_lib_analysis_DIR)/AnalysisDeclContext.cpp \
  $(clang_lib_analysis_DIR)/BodyFarm.cpp \
  $(clang_lib_analysis_DIR)/CFG.cpp \
  $(clang_lib_analysis_DIR)/CFGReachabilityAnalysis.cpp \
  $(clang_lib_analysis_DIR)/CFGStmtMap.cpp \
  $(clang_lib_analysis_DIR)/CallGraph.cpp \
  $(clang_lib_analysis_DIR)/CloneDetection.cpp \
  $(clang_lib_analysis_DIR)/CocoaConventions.cpp \
  $(clang_lib_analysis_DIR)/CodeInjector.cpp \
  $(clang_lib_analysis_DIR)/ConstructionContext.cpp \
  $(clang_lib_analysis_DIR)/Consumed.cpp \
  $(clang_lib_analysis_DIR)/Dominators.cpp \
  $(clang_lib_analysis_DIR)/FormatString.cpp \
  $(clang_lib_analysis_DIR)/LiveVariables.cpp \
  $(clang_lib_analysis_DIR)/OSLog.cpp \
  $(clang_lib_analysis_DIR)/ObjCNoReturn.cpp \
  $(clang_lib_analysis_DIR)/PostOrderCFGView.cpp \
  $(clang_lib_analysis_DIR)/PrintfFormatString.cpp \
  $(clang_lib_analysis_DIR)/ProgramPoint.cpp \
  $(clang_lib_analysis_DIR)/PseudoConstantAnalysis.cpp \
  $(clang_lib_analysis_DIR)/ReachableCode.cpp \
  $(clang_lib_analysis_DIR)/ScanfFormatString.cpp \
  $(clang_lib_analysis_DIR)/ThreadSafety.cpp \
  $(clang_lib_analysis_DIR)/ThreadSafetyCommon.cpp \
  $(clang_lib_analysis_DIR)/ThreadSafetyLogical.cpp \
  $(clang_lib_analysis_DIR)/ThreadSafetyTIL.cpp \
  $(clang_lib_analysis_DIR)/UninitializedValues.cpp



clang_lib_ast_DIR=$(CLANG_ROOT_PATH)/lib/AST

clang_lib_ast_SRC_FILES:= \
  $(clang_lib_ast_DIR)/APValue.cpp \
  $(clang_lib_ast_DIR)/ASTConsumer.cpp \
  $(clang_lib_ast_DIR)/ASTContext.cpp \
  $(clang_lib_ast_DIR)/ASTDiagnostic.cpp \
  $(clang_lib_ast_DIR)/ASTDumper.cpp \
  $(clang_lib_ast_DIR)/ASTImporter.cpp \
  $(clang_lib_ast_DIR)/ASTStructuralEquivalence.cpp \
  $(clang_lib_ast_DIR)/ASTTypeTraits.cpp \
  $(clang_lib_ast_DIR)/AttrImpl.cpp \
  $(clang_lib_ast_DIR)/CXXInheritance.cpp \
  $(clang_lib_ast_DIR)/Comment.cpp \
  $(clang_lib_ast_DIR)/CommentBriefParser.cpp \
  $(clang_lib_ast_DIR)/CommentCommandTraits.cpp \
  $(clang_lib_ast_DIR)/CommentLexer.cpp \
  $(clang_lib_ast_DIR)/CommentParser.cpp \
  $(clang_lib_ast_DIR)/CommentSema.cpp \
  $(clang_lib_ast_DIR)/ComparisonCategories.cpp \
  $(clang_lib_ast_DIR)/DataCollection.cpp \
  $(clang_lib_ast_DIR)/Decl.cpp \
  $(clang_lib_ast_DIR)/DeclBase.cpp \
  $(clang_lib_ast_DIR)/DeclCXX.cpp \
  $(clang_lib_ast_DIR)/DeclFriend.cpp \
  $(clang_lib_ast_DIR)/DeclGroup.cpp \
  $(clang_lib_ast_DIR)/DeclObjC.cpp \
  $(clang_lib_ast_DIR)/DeclOpenMP.cpp \
  $(clang_lib_ast_DIR)/DeclPrinter.cpp \
  $(clang_lib_ast_DIR)/DeclTemplate.cpp \
  $(clang_lib_ast_DIR)/DeclarationName.cpp \
  $(clang_lib_ast_DIR)/Expr.cpp \
  $(clang_lib_ast_DIR)/ExprCXX.cpp \
  $(clang_lib_ast_DIR)/ExprClassification.cpp \
  $(clang_lib_ast_DIR)/ExprConstant.cpp \
  $(clang_lib_ast_DIR)/ExprObjC.cpp \
  $(clang_lib_ast_DIR)/ExternalASTMerger.cpp \
  $(clang_lib_ast_DIR)/ExternalASTSource.cpp \
  $(clang_lib_ast_DIR)/InheritViz.cpp \
  $(clang_lib_ast_DIR)/ItaniumCXXABI.cpp \
  $(clang_lib_ast_DIR)/ItaniumMangle.cpp \
  $(clang_lib_ast_DIR)/Mangle.cpp \
  $(clang_lib_ast_DIR)/MicrosoftCXXABI.cpp \
  $(clang_lib_ast_DIR)/MicrosoftMangle.cpp \
  $(clang_lib_ast_DIR)/NSAPI.cpp \
  $(clang_lib_ast_DIR)/NestedNameSpecifier.cpp \
  $(clang_lib_ast_DIR)/ODRHash.cpp \
  $(clang_lib_ast_DIR)/OpenMPClause.cpp \
  $(clang_lib_ast_DIR)/ParentMap.cpp \
  $(clang_lib_ast_DIR)/QualTypeNames.cpp \
  $(clang_lib_ast_DIR)/RawCommentList.cpp \
  $(clang_lib_ast_DIR)/RecordLayout.cpp \
  $(clang_lib_ast_DIR)/RecordLayoutBuilder.cpp \
  $(clang_lib_ast_DIR)/SelectorLocationsKind.cpp \
  $(clang_lib_ast_DIR)/Stmt.cpp \
  $(clang_lib_ast_DIR)/StmtCXX.cpp \
  $(clang_lib_ast_DIR)/StmtIterator.cpp \
  $(clang_lib_ast_DIR)/StmtObjC.cpp \
  $(clang_lib_ast_DIR)/StmtOpenMP.cpp \
  $(clang_lib_ast_DIR)/StmtPrinter.cpp \
  $(clang_lib_ast_DIR)/StmtProfile.cpp \
  $(clang_lib_ast_DIR)/StmtViz.cpp \
  $(clang_lib_ast_DIR)/TemplateBase.cpp \
  $(clang_lib_ast_DIR)/TemplateName.cpp \
  $(clang_lib_ast_DIR)/Type.cpp \
  $(clang_lib_ast_DIR)/TypeLoc.cpp \
  $(clang_lib_ast_DIR)/TypePrinter.cpp \
  $(clang_lib_ast_DIR)/VTTBuilder.cpp \
  $(clang_lib_ast_DIR)/VTableBuilder.cpp



clang_lib_astmatchers_DIR=$(CLANG_ROOT_PATH)/lib/ASTMatchers

clang_lib_astmatchers_SRC_FILES:= \
  $(clang_lib_astmatchers_DIR)/ASTMatchFinder.cpp \
  $(clang_lib_astmatchers_DIR)/ASTMatchersInternal.cpp



clang_lib_basic_DIR=$(CLANG_ROOT_PATH)/lib/Basic

clang_lib_basic_SRC_FILES:= \
  $(clang_lib_basic_DIR)/Attributes.cpp \
  $(clang_lib_basic_DIR)/Builtins.cpp \
  $(clang_lib_basic_DIR)/CharInfo.cpp \
  $(clang_lib_basic_DIR)/Cuda.cpp \
  $(clang_lib_basic_DIR)/Diagnostic.cpp \
  $(clang_lib_basic_DIR)/DiagnosticIDs.cpp \
  $(clang_lib_basic_DIR)/DiagnosticOptions.cpp \
  $(clang_lib_basic_DIR)/FileManager.cpp \
  $(clang_lib_basic_DIR)/FileSystemStatCache.cpp \
  $(clang_lib_basic_DIR)/IdentifierTable.cpp \
  $(clang_lib_basic_DIR)/LangOptions.cpp \
  $(clang_lib_basic_DIR)/MemoryBufferCache.cpp \
  $(clang_lib_basic_DIR)/Module.cpp \
  $(clang_lib_basic_DIR)/ObjCRuntime.cpp \
  $(clang_lib_basic_DIR)/OpenMPKinds.cpp \
  $(clang_lib_basic_DIR)/OperatorPrecedence.cpp \
  $(clang_lib_basic_DIR)/SanitizerBlacklist.cpp \
  $(clang_lib_basic_DIR)/SanitizerSpecialCaseList.cpp \
  $(clang_lib_basic_DIR)/Sanitizers.cpp \
  $(clang_lib_basic_DIR)/SourceLocation.cpp \
  $(clang_lib_basic_DIR)/SourceManager.cpp \
  $(clang_lib_basic_DIR)/TargetInfo.cpp \
  $(clang_lib_basic_DIR)/Targets.cpp \
  $(clang_lib_basic_DIR)/TokenKinds.cpp \
  $(clang_lib_basic_DIR)/Version.cpp \
  $(clang_lib_basic_DIR)/VirtualFileSystem.cpp \
  $(clang_lib_basic_DIR)/Warnings.cpp \
  $(clang_lib_basic_DIR)/XRayInstr.cpp \
  $(clang_lib_basic_DIR)/XRayLists.cpp \
  $(clang_lib_basic_DIR)/Targets/AArch64.cpp \
  $(clang_lib_basic_DIR)/Targets/AMDGPU.cpp \
  $(clang_lib_basic_DIR)/Targets/ARM.cpp \
  $(clang_lib_basic_DIR)/Targets/AVR.cpp \
  $(clang_lib_basic_DIR)/Targets/BPF.cpp \
  $(clang_lib_basic_DIR)/Targets/Hexagon.cpp \
  $(clang_lib_basic_DIR)/Targets/Lanai.cpp \
  $(clang_lib_basic_DIR)/Targets/Le64.cpp \
  $(clang_lib_basic_DIR)/Targets/MSP430.cpp \
  $(clang_lib_basic_DIR)/Targets/Mips.cpp \
  $(clang_lib_basic_DIR)/Targets/NVPTX.cpp \
  $(clang_lib_basic_DIR)/Targets/Nios2.cpp \
  $(clang_lib_basic_DIR)/Targets/OSTargets.cpp \
  $(clang_lib_basic_DIR)/Targets/PNaCl.cpp \
  $(clang_lib_basic_DIR)/Targets/PPC.cpp \
  $(clang_lib_basic_DIR)/Targets/RISCV.cpp \
  $(clang_lib_basic_DIR)/Targets/SPIR.cpp \
  $(clang_lib_basic_DIR)/Targets/Sparc.cpp \
  $(clang_lib_basic_DIR)/Targets/SystemZ.cpp \
  $(clang_lib_basic_DIR)/Targets/TCE.cpp \
  $(clang_lib_basic_DIR)/Targets/WebAssembly.cpp \
  $(clang_lib_basic_DIR)/Targets/X86.cpp \
  $(clang_lib_basic_DIR)/Targets/XCore.cpp 



clang_lib_driver_DIR=$(CLANG_ROOT_PATH)/lib/Driver

clang_lib_driver_SRC_FILES:= \
  $(clang_lib_driver_DIR)/Action.cpp \
  $(clang_lib_driver_DIR)/Compilation.cpp \
  $(clang_lib_driver_DIR)/Distro.cpp \
  $(clang_lib_driver_DIR)/Driver.cpp \
  $(clang_lib_driver_DIR)/DriverOptions.cpp \
  $(clang_lib_driver_DIR)/Job.cpp \
  $(clang_lib_driver_DIR)/Multilib.cpp \
  $(clang_lib_driver_DIR)/Phases.cpp \
  $(clang_lib_driver_DIR)/SanitizerArgs.cpp \
  $(clang_lib_driver_DIR)/Tool.cpp \
  $(clang_lib_driver_DIR)/ToolChain.cpp \
  $(clang_lib_driver_DIR)/ToolChains/AMDGPU.cpp \
  $(clang_lib_driver_DIR)/ToolChains/AVR.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Ananas.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Arch/AArch64.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Arch/ARM.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Arch/Mips.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Arch/PPC.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Arch/RISCV.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Arch/Sparc.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Arch/SystemZ.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Arch/X86.cpp \
  $(clang_lib_driver_DIR)/ToolChains/BareMetal.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Clang.cpp \
  $(clang_lib_driver_DIR)/ToolChains/CloudABI.cpp \
  $(clang_lib_driver_DIR)/ToolChains/CommonArgs.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Contiki.cpp \
  $(clang_lib_driver_DIR)/ToolChains/CrossWindows.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Cuda.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Darwin.cpp \
  $(clang_lib_driver_DIR)/ToolChains/DragonFly.cpp \
  $(clang_lib_driver_DIR)/ToolChains/FreeBSD.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Fuchsia.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Gnu.cpp \
  $(clang_lib_driver_DIR)/ToolChains/HIP.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Haiku.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Hexagon.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Linux.cpp \
  $(clang_lib_driver_DIR)/ToolChains/MSVC.cpp \
  $(clang_lib_driver_DIR)/ToolChains/MinGW.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Minix.cpp \
  $(clang_lib_driver_DIR)/ToolChains/MipsLinux.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Myriad.cpp \
  $(clang_lib_driver_DIR)/ToolChains/NaCl.cpp \
  $(clang_lib_driver_DIR)/ToolChains/NetBSD.cpp \
  $(clang_lib_driver_DIR)/ToolChains/OpenBSD.cpp \
  $(clang_lib_driver_DIR)/ToolChains/PS4CPU.cpp \
  $(clang_lib_driver_DIR)/ToolChains/RISCV.cpp \
  $(clang_lib_driver_DIR)/ToolChains/Solaris.cpp \
  $(clang_lib_driver_DIR)/ToolChains/TCE.cpp \
  $(clang_lib_driver_DIR)/ToolChains/WebAssembly.cpp \
  $(clang_lib_driver_DIR)/ToolChains/XCore.cpp \
  $(clang_lib_driver_DIR)/Types.cpp \
  $(clang_lib_driver_DIR)/XRayArgs.cpp



clang_lib_edit_DIR=$(CLANG_ROOT_PATH)/lib/Edit

clang_lib_edit_SRC_FILES:= \
  $(clang_lib_edit_DIR)/Commit.cpp \
  $(clang_lib_edit_DIR)/EditedSource.cpp \
  $(clang_lib_edit_DIR)/RewriteObjCFoundationAPI.cpp



clang_lib_format_DIR=$(CLANG_ROOT_PATH)/lib/Format

clang_lib_format_SRC_FILES:= \
  $(clang_lib_format_DIR)/AffectedRangeManager.cpp \
  $(clang_lib_format_DIR)/BreakableToken.cpp \
  $(clang_lib_format_DIR)/ContinuationIndenter.cpp \
  $(clang_lib_format_DIR)/Format.cpp \
  $(clang_lib_format_DIR)/FormatToken.cpp \
  $(clang_lib_format_DIR)/FormatTokenLexer.cpp \
  $(clang_lib_format_DIR)/NamespaceEndCommentsFixer.cpp \
  $(clang_lib_format_DIR)/SortJavaScriptImports.cpp \
  $(clang_lib_format_DIR)/TokenAnalyzer.cpp \
  $(clang_lib_format_DIR)/TokenAnnotator.cpp \
  $(clang_lib_format_DIR)/UnwrappedLineFormatter.cpp \
  $(clang_lib_format_DIR)/UnwrappedLineParser.cpp \
  $(clang_lib_format_DIR)/UsingDeclarationsSorter.cpp \
  $(clang_lib_format_DIR)/WhitespaceManager.cpp



clang_lib_frontend_DIR=$(CLANG_ROOT_PATH)/lib/Frontend

clang_lib_frontend_SRC_FILES:= \
  $(clang_lib_frontend_DIR)/ASTConsumers.cpp \
  $(clang_lib_frontend_DIR)/ASTMerge.cpp \
  $(clang_lib_frontend_DIR)/ASTUnit.cpp \
  $(clang_lib_frontend_DIR)/CacheTokens.cpp \
  $(clang_lib_frontend_DIR)/ChainedDiagnosticConsumer.cpp \
  $(clang_lib_frontend_DIR)/ChainedIncludesSource.cpp \
  $(clang_lib_frontend_DIR)/CodeGenOptions.cpp \
  $(clang_lib_frontend_DIR)/CompilerInstance.cpp \
  $(clang_lib_frontend_DIR)/CompilerInvocation.cpp \
  $(clang_lib_frontend_DIR)/CreateInvocationFromCommandLine.cpp \
  $(clang_lib_frontend_DIR)/DependencyFile.cpp \
  $(clang_lib_frontend_DIR)/DependencyGraph.cpp \
  $(clang_lib_frontend_DIR)/DiagnosticRenderer.cpp \
  $(clang_lib_frontend_DIR)/FrontendAction.cpp \
  $(clang_lib_frontend_DIR)/FrontendActions.cpp \
  $(clang_lib_frontend_DIR)/FrontendOptions.cpp \
  $(clang_lib_frontend_DIR)/FrontendTiming.cpp \
  $(clang_lib_frontend_DIR)/HeaderIncludeGen.cpp \
  $(clang_lib_frontend_DIR)/InitHeaderSearch.cpp \
  $(clang_lib_frontend_DIR)/InitPreprocessor.cpp \
  $(clang_lib_frontend_DIR)/LangStandards.cpp \
  $(clang_lib_frontend_DIR)/LayoutOverrideSource.cpp \
  $(clang_lib_frontend_DIR)/LogDiagnosticPrinter.cpp \
  $(clang_lib_frontend_DIR)/ModuleDependencyCollector.cpp \
  $(clang_lib_frontend_DIR)/MultiplexConsumer.cpp \
  $(clang_lib_frontend_DIR)/PCHContainerOperations.cpp \
  $(clang_lib_frontend_DIR)/PrecompiledPreamble.cpp \
  $(clang_lib_frontend_DIR)/PrintPreprocessedOutput.cpp \
  $(clang_lib_frontend_DIR)/SerializedDiagnosticPrinter.cpp \
  $(clang_lib_frontend_DIR)/SerializedDiagnosticReader.cpp \
  $(clang_lib_frontend_DIR)/TestModuleFileExtension.cpp \
  $(clang_lib_frontend_DIR)/TextDiagnostic.cpp \
  $(clang_lib_frontend_DIR)/TextDiagnosticBuffer.cpp \
  $(clang_lib_frontend_DIR)/TextDiagnosticPrinter.cpp \
  $(clang_lib_frontend_DIR)/VerifyDiagnosticConsumer.cpp



clang_lib_index_DIR=$(CLANG_ROOT_PATH)/lib/Index

clang_lib_index_SRC_FILES:= \
  $(clang_lib_index_DIR)/CodegenNameGenerator.cpp \
  $(clang_lib_index_DIR)/CommentToXML.cpp \
  $(clang_lib_index_DIR)/IndexBody.cpp \
  $(clang_lib_index_DIR)/IndexDecl.cpp \
  $(clang_lib_index_DIR)/IndexSymbol.cpp \
  $(clang_lib_index_DIR)/IndexTypeSourceInfo.cpp \
  $(clang_lib_index_DIR)/IndexingAction.cpp \
  $(clang_lib_index_DIR)/IndexingContext.cpp \
  $(clang_lib_index_DIR)/USRGeneration.cpp



clang_lib_lex_DIR=$(CLANG_ROOT_PATH)/lib/Lex

clang_lib_lex_SRC_FILES:= \
  $(clang_lib_lex_DIR)/HeaderMap.cpp \
  $(clang_lib_lex_DIR)/HeaderSearch.cpp \
  $(clang_lib_lex_DIR)/Lexer.cpp \
  $(clang_lib_lex_DIR)/LiteralSupport.cpp \
  $(clang_lib_lex_DIR)/MacroArgs.cpp \
  $(clang_lib_lex_DIR)/MacroInfo.cpp \
  $(clang_lib_lex_DIR)/ModuleMap.cpp \
  $(clang_lib_lex_DIR)/PPCaching.cpp \
  $(clang_lib_lex_DIR)/PPCallbacks.cpp \
  $(clang_lib_lex_DIR)/PPConditionalDirectiveRecord.cpp \
  $(clang_lib_lex_DIR)/PPDirectives.cpp \
  $(clang_lib_lex_DIR)/PPExpressions.cpp \
  $(clang_lib_lex_DIR)/PPLexerChange.cpp \
  $(clang_lib_lex_DIR)/PPMacroExpansion.cpp \
  $(clang_lib_lex_DIR)/PTHLexer.cpp \
  $(clang_lib_lex_DIR)/Pragma.cpp \
  $(clang_lib_lex_DIR)/PreprocessingRecord.cpp \
  $(clang_lib_lex_DIR)/Preprocessor.cpp \
  $(clang_lib_lex_DIR)/PreprocessorLexer.cpp \
  $(clang_lib_lex_DIR)/ScratchBuffer.cpp \
  $(clang_lib_lex_DIR)/TokenConcatenation.cpp \
  $(clang_lib_lex_DIR)/TokenLexer.cpp



clang_lib_parse_DIR=$(CLANG_ROOT_PATH)/lib/Parse

clang_lib_parse_SRC_FILES:= \
  $(clang_lib_parse_DIR)/ParseAST.cpp \
  $(clang_lib_parse_DIR)/ParseCXXInlineMethods.cpp \
  $(clang_lib_parse_DIR)/ParseDecl.cpp \
  $(clang_lib_parse_DIR)/ParseDeclCXX.cpp \
  $(clang_lib_parse_DIR)/ParseExpr.cpp \
  $(clang_lib_parse_DIR)/ParseExprCXX.cpp \
  $(clang_lib_parse_DIR)/ParseInit.cpp \
  $(clang_lib_parse_DIR)/ParseObjc.cpp \
  $(clang_lib_parse_DIR)/ParseOpenMP.cpp \
  $(clang_lib_parse_DIR)/ParsePragma.cpp \
  $(clang_lib_parse_DIR)/ParseStmt.cpp \
  $(clang_lib_parse_DIR)/ParseStmtAsm.cpp \
  $(clang_lib_parse_DIR)/ParseTemplate.cpp \
  $(clang_lib_parse_DIR)/ParseTentative.cpp \
  $(clang_lib_parse_DIR)/Parser.cpp



clang_lib_rewrite_DIR=$(CLANG_ROOT_PATH)/lib/Rewrite

clang_lib_rewrite_SRC_FILES:= \
  $(clang_lib_rewrite_DIR)/DeltaTree.cpp \
  $(clang_lib_rewrite_DIR)/HTMLRewrite.cpp \
  $(clang_lib_rewrite_DIR)/RewriteRope.cpp \
  $(clang_lib_rewrite_DIR)/Rewriter.cpp \
  $(clang_lib_rewrite_DIR)/TokenRewriter.cpp



clang_lib_sema_DIR=$(CLANG_ROOT_PATH)/lib/Sema

clang_lib_sema_SRC_FILES:= \
  $(clang_lib_sema_DIR)/AnalysisBasedWarnings.cpp \
  $(clang_lib_sema_DIR)/CodeCompleteConsumer.cpp \
  $(clang_lib_sema_DIR)/DeclSpec.cpp \
  $(clang_lib_sema_DIR)/DelayedDiagnostic.cpp \
  $(clang_lib_sema_DIR)/IdentifierResolver.cpp \
  $(clang_lib_sema_DIR)/JumpDiagnostics.cpp \
  $(clang_lib_sema_DIR)/MultiplexExternalSemaSource.cpp \
  $(clang_lib_sema_DIR)/ParsedAttr.cpp \
  $(clang_lib_sema_DIR)/Scope.cpp \
  $(clang_lib_sema_DIR)/ScopeInfo.cpp \
  $(clang_lib_sema_DIR)/Sema.cpp \
  $(clang_lib_sema_DIR)/SemaAccess.cpp \
  $(clang_lib_sema_DIR)/SemaAttr.cpp \
  $(clang_lib_sema_DIR)/SemaCUDA.cpp \
  $(clang_lib_sema_DIR)/SemaCXXScopeSpec.cpp \
  $(clang_lib_sema_DIR)/SemaCast.cpp \
  $(clang_lib_sema_DIR)/SemaChecking.cpp \
  $(clang_lib_sema_DIR)/SemaCodeComplete.cpp \
  $(clang_lib_sema_DIR)/SemaConsumer.cpp \
  $(clang_lib_sema_DIR)/SemaCoroutine.cpp \
  $(clang_lib_sema_DIR)/SemaDecl.cpp \
  $(clang_lib_sema_DIR)/SemaDeclAttr.cpp \
  $(clang_lib_sema_DIR)/SemaDeclCXX.cpp \
  $(clang_lib_sema_DIR)/SemaDeclObjC.cpp \
  $(clang_lib_sema_DIR)/SemaExceptionSpec.cpp \
  $(clang_lib_sema_DIR)/SemaExpr.cpp \
  $(clang_lib_sema_DIR)/SemaExprCXX.cpp \
  $(clang_lib_sema_DIR)/SemaExprMember.cpp \
  $(clang_lib_sema_DIR)/SemaExprObjC.cpp \
  $(clang_lib_sema_DIR)/SemaFixItUtils.cpp \
  $(clang_lib_sema_DIR)/SemaInit.cpp \
  $(clang_lib_sema_DIR)/SemaLambda.cpp \
  $(clang_lib_sema_DIR)/SemaLookup.cpp \
  $(clang_lib_sema_DIR)/SemaObjCProperty.cpp \
  $(clang_lib_sema_DIR)/SemaOpenMP.cpp \
  $(clang_lib_sema_DIR)/SemaOverload.cpp \
  $(clang_lib_sema_DIR)/SemaPseudoObject.cpp \
  $(clang_lib_sema_DIR)/SemaStmt.cpp \
  $(clang_lib_sema_DIR)/SemaStmtAsm.cpp \
  $(clang_lib_sema_DIR)/SemaStmtAttr.cpp \
  $(clang_lib_sema_DIR)/SemaTemplate.cpp \
  $(clang_lib_sema_DIR)/SemaTemplateDeduction.cpp \
  $(clang_lib_sema_DIR)/SemaTemplateInstantiate.cpp \
  $(clang_lib_sema_DIR)/SemaTemplateInstantiateDecl.cpp \
  $(clang_lib_sema_DIR)/SemaTemplateVariadic.cpp \
  $(clang_lib_sema_DIR)/SemaType.cpp \
  $(clang_lib_sema_DIR)/TypeLocBuilder.cpp



clang_lib_serialization_DIR=$(CLANG_ROOT_PATH)/lib/Serialization

clang_lib_serialization_SRC_FILES:= \
  $(clang_lib_serialization_DIR)/ASTCommon.cpp \
  $(clang_lib_serialization_DIR)/ASTReader.cpp \
  $(clang_lib_serialization_DIR)/ASTReaderDecl.cpp \
  $(clang_lib_serialization_DIR)/ASTReaderStmt.cpp \
  $(clang_lib_serialization_DIR)/ASTWriter.cpp \
  $(clang_lib_serialization_DIR)/ASTWriterDecl.cpp \
  $(clang_lib_serialization_DIR)/ASTWriterStmt.cpp \
  $(clang_lib_serialization_DIR)/GeneratePCH.cpp \
  $(clang_lib_serialization_DIR)/GlobalModuleIndex.cpp \
  $(clang_lib_serialization_DIR)/Module.cpp \
  $(clang_lib_serialization_DIR)/ModuleFileExtension.cpp \
  $(clang_lib_serialization_DIR)/ModuleManager.cpp



clang_lib_tooling_DIR=$(CLANG_ROOT_PATH)/lib/Tooling

clang_lib_tooling_SRC_FILES:= \
  $(clang_lib_tooling_DIR)/AllTUsExecution.cpp \
  $(clang_lib_tooling_DIR)/ArgumentsAdjusters.cpp \
  $(clang_lib_tooling_DIR)/CommonOptionsParser.cpp \
  $(clang_lib_tooling_DIR)/CompilationDatabase.cpp \
  $(clang_lib_tooling_DIR)/Execution.cpp \
  $(clang_lib_tooling_DIR)/FileMatchTrie.cpp \
  $(clang_lib_tooling_DIR)/FixIt.cpp \
  $(clang_lib_tooling_DIR)/InterpolatingCompilationDatabase.cpp \
  $(clang_lib_tooling_DIR)/JSONCompilationDatabase.cpp \
  $(clang_lib_tooling_DIR)/Refactoring.cpp \
  $(clang_lib_tooling_DIR)/RefactoringCallbacks.cpp \
  $(clang_lib_tooling_DIR)/StandaloneExecution.cpp \
  $(clang_lib_tooling_DIR)/Tooling.cpp



clang_lib_toolingcore_DIR=$(CLANG_ROOT_PATH)/lib/Tooling/Core

clang_lib_toolingcore_SRC_FILES:= \
  $(clang_lib_toolingcore_DIR)/Diagnostic.cpp \
  $(clang_lib_toolingcore_DIR)/Lookup.cpp \
  $(clang_lib_toolingcore_DIR)/Replacement.cpp



